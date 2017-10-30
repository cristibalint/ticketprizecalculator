package com.github.cristibalint.lottery.javamillions.policy;

import com.github.cristibalint.lottery.LotteryPolicy;
import com.github.cristibalint.lottery.Ticket;
import com.github.cristibalint.lottery.javamillions.JavaMillionsLottery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class JavaMillionsBasePolicy implements LotteryPolicy{

    JavaMillionsLottery lottery;

    public JavaMillionsBasePolicy(JavaMillionsLottery lottery) {
        this.lottery = lottery;
    }

    protected abstract int getPool1Matches();

    protected abstract int getPool2Matches();

    @Override
    public boolean isSatisfiedBy(Ticket ticket) {
        return getPool1Matches() == ticket.getNumbers().subList(0, JavaMillionsLottery.POOL_1_SIZE).stream()
                .filter(lottery.getWinningNumbers()::contains)
                .count()
                &&
                getPool2Matches() == ticket.getNumbers().subList(JavaMillionsLottery.POOL_1_SIZE, lottery.getWinningNumbers().size()).stream()
                        .filter(lottery.getWinningNumbers().subList(JavaMillionsLottery.POOL_1_SIZE, lottery.getWinningNumbers().size())::contains)
                        .count();
    }

    @Override
    public Map<String, List<Integer>> getMatchingNumbers(Ticket ticket) {

        Map<String, List<Integer>> numbersDistribution = new HashMap<>();

        List<Integer> pool1Numbers = ticket.getNumbers().subList(0, JavaMillionsLottery.POOL_1_SIZE).stream()
                .filter(lottery.getWinningNumbers().subList(0, JavaMillionsLottery.POOL_1_SIZE)::contains)
                .sorted()
                .collect(Collectors.toList());

        List<Integer> pool2Numbers = ticket.getNumbers().subList(JavaMillionsLottery.POOL_1_SIZE, lottery.getWinningNumbers().size()).stream()
                .filter(lottery.getWinningNumbers().subList(JavaMillionsLottery.POOL_1_SIZE, lottery.getWinningNumbers().size())::contains)
                .sorted()
                .collect(Collectors.toList());

        if (pool1Numbers.size() > 0) {
            numbersDistribution.put("pool 1", pool1Numbers);
        }
        if(pool2Numbers.size() > 0) {
            numbersDistribution.put("pool 2", pool2Numbers);
        }

        return numbersDistribution;
    }
}
