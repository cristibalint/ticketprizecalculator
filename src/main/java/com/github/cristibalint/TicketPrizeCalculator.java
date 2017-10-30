package com.github.cristibalint;

import com.github.cristibalint.lottery.Lottery;
import com.github.cristibalint.lottery.LotteryFactory;
import com.github.cristibalint.lottery.Ticket;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketPrizeCalculator {

    public static void main(String... args) {

        System.out.println("Balint Cristian - Calculating Lottery Winners v1.0.0");

        if (args.length != 3) {
            System.out.println("Please provide 3 parameters. Lottery type, winning numbers, and ticket numbers.");
            return;
        }

        String lotteryType = args[0];
        String winningNumbers = args[1];
        String ticketNumbers = args[2];

        try {
            Lottery lottery = LotteryFactory.createLottery(lotteryType, winningNumbers);
            Ticket ticket = LotteryFactory.createTicket(lotteryType, ticketNumbers);

            if (lottery.isWinner(ticket)) {
                System.out.printf("This ticket won a prize of class %s and amount \u00A3%s\n",
                        lottery.getPrizeClass(ticket),
                        lottery.getPrizeAmount(ticket)
                );

                Map<String, List<Integer>> numbersDistribution = lottery.getNumbersDistribution(ticket);

                String matches = numbersDistribution.entrySet().stream()
                        .map(entry -> {
                            final List<Integer> numbers = entry.getValue();
                            return numbers.size() > 1
                                    ? "the numbers " + numbers.stream().map(String::valueOf).collect(Collectors.joining(",")) + " from " + entry.getKey()
                                    : "the number " + numbers.get(0) + " from " + entry.getKey();
                        })
                        .collect(Collectors.joining(" and "));

                System.out.printf("Matched %s", matches);
            } else {
                System.out.println("The ticket did not win a prize.");
            }

        } catch (RuntimeException e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }
    }
}
