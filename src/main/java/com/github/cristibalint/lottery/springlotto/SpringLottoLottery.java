package com.github.cristibalint.lottery.springlotto;

import com.github.cristibalint.lottery.Lottery;
import com.github.cristibalint.lottery.LotteryPolicy;
import com.github.cristibalint.lottery.Ticket;
import com.github.cristibalint.lottery.exceptions.DuplicateNumber;
import com.github.cristibalint.lottery.exceptions.NumberOutOfRange;
import com.github.cristibalint.lottery.exceptions.WrongAmountOfNumbers;
import com.github.cristibalint.lottery.springlotto.policy.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpringLottoLottery implements Lottery {

    public static final int NUMBERS_COUNT = 6;
    public static final int NUMBERS_LOW_LIMIT = 1;
    public static final int NUMBERS_HIGH_LIMIT = 36;

    private final List<Integer> numbers;

    private LotteryPolicy[] policies =  {
            new SpringLottoClass1Policy(this),
            new SpringLottoClass2Policy(this),
            new SpringLottoClass3Policy(this),
            new SpringLottoClass4Policy(this),
            new SpringLottoClass5Policy(this),
            new SpringLottoClass6Policy(this)
    };

    public SpringLottoLottery(String winningNumbers) {
        numbers = Stream.of(winningNumbers.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        if (numbers.size() != NUMBERS_COUNT) {
            throw new WrongAmountOfNumbers();
        }

        validate(numbers);
    }

    @Override
    public boolean isWinner(Ticket ticket) {
        return Stream.of(policies)
                .map(policy -> policy.isSatisfiedBy(ticket))
                .reduce((r, r2) -> r || r2)
                .orElse(false);
    }

    @Override
    public List<Integer> getWinningNumbers() {
        return numbers;
    }

    @Override
    public int getPrizeClass(Ticket ticket) {
        return getMatchingPolicy(ticket).getPrizeClass();
    }

    @Override
    public Integer getPrizeAmount(Ticket ticket) {
        return getMatchingPolicy(ticket).getPrizeAmount();
    }

    @Override
    public Map<String, List<Integer>> getNumbersDistribution(Ticket ticket) {
        return getMatchingPolicy(ticket).getMatchingNumbers(ticket);
    }

    private LotteryPolicy getMatchingPolicy(Ticket ticket) {
        return Stream.of(policies)
                .filter(policy -> policy.isSatisfiedBy(ticket))
                .findFirst()
                .orElse(new SpringLottoClass0Policy(this));
    }

    public static void validate(List<Integer> numbers) {
        numbers.stream()
                .filter(number -> number < NUMBERS_LOW_LIMIT || number > NUMBERS_HIGH_LIMIT)
                .collect(Collectors.toList())
                .forEach(number -> {
                    throw new NumberOutOfRange(number, NUMBERS_LOW_LIMIT, NUMBERS_HIGH_LIMIT);
                });

        numbers.forEach(entry -> {
            if (numbers.indexOf(entry) != numbers.lastIndexOf(entry)) {
                throw new DuplicateNumber(entry);
            }
        });
    }
}
