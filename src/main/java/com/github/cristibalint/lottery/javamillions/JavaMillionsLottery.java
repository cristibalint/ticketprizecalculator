package com.github.cristibalint.lottery.javamillions;

import com.github.cristibalint.lottery.Lottery;
import com.github.cristibalint.lottery.LotteryPolicy;
import com.github.cristibalint.lottery.Ticket;
import com.github.cristibalint.lottery.exceptions.DuplicateNumber;
import com.github.cristibalint.lottery.exceptions.NumberOutOfRange;
import com.github.cristibalint.lottery.exceptions.WrongAmountOfNumbers;
import com.github.cristibalint.lottery.javamillions.policy.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaMillionsLottery implements Lottery {

    public static final int POOL_1_SIZE = 5;
    public static final int NUMBERS_COUNT = 6;
    public static final int NUMBERS_LOW_LIMIT = 1;
    public static final int NUMBERS_HIGH_LIMIT_POOL1 = 49;
    public static final int NUMBERS_HIGH_LIMIT_POOL2 = 9;

    private final List<Integer> numbers;

    private LotteryPolicy[] policies =  {
            new JavaMillionsClass1Policy(this),
            new JavaMillionsClass2Policy(this),
            new JavaMillionsClass3Policy(this),
            new JavaMillionsClass4Policy(this),
            new JavaMillionsClass5Policy(this),
            new JavaMillionsClass6Policy(this),
            new JavaMillionsClass7Policy(this),
            new JavaMillionsClass8Policy(this)
    };

    public JavaMillionsLottery(String winningNumbers) {

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
                .orElse(new JavaMillionsClass0Policy(this));
    }

    public static void validate(List<Integer> numbers) {
        final List<Integer> pool1 = numbers.subList(0, NUMBERS_COUNT - 1);
        final List<Integer> pool2 = numbers.subList(NUMBERS_COUNT - 1, NUMBERS_COUNT);

        pool1.stream()
                .filter(number -> number < NUMBERS_LOW_LIMIT || number > NUMBERS_HIGH_LIMIT_POOL1)
                .collect(Collectors.toList())
                .forEach(number -> {
                    throw new NumberOutOfRange(number, NUMBERS_LOW_LIMIT, NUMBERS_HIGH_LIMIT_POOL1);
                });

        pool2.stream()
                .filter(number -> number < NUMBERS_LOW_LIMIT || number > NUMBERS_HIGH_LIMIT_POOL2)
                .collect(Collectors.toList())
                .forEach(number -> {
                    throw new NumberOutOfRange(number, NUMBERS_LOW_LIMIT, NUMBERS_HIGH_LIMIT_POOL2);
                });

        pool1.forEach(entry -> {
            if (pool1.indexOf(entry) != pool1.lastIndexOf(entry)) {
                throw new DuplicateNumber(entry);
            }
        });
    }
}
