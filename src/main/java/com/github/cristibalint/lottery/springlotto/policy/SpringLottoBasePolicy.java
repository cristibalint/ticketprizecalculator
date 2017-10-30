package com.github.cristibalint.lottery.springlotto.policy;

import com.github.cristibalint.lottery.LotteryPolicy;
import com.github.cristibalint.lottery.Ticket;
import com.github.cristibalint.lottery.springlotto.SpringLottoLottery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class SpringLottoBasePolicy implements LotteryPolicy {

    SpringLottoLottery lottery;

    public SpringLottoBasePolicy(SpringLottoLottery lottery) {
        this.lottery = lottery;
    }

    protected abstract int getMatches();


    @Override
    public boolean isSatisfiedBy(Ticket ticket) {
        return getMatches() == ticket.getNumbers().stream()
                .filter(lottery.getWinningNumbers()::contains)
                .count();
    }

    @Override
    public Map<String, List<Integer>> getMatchingNumbers(Ticket ticket) {

        Map<String, List<Integer>> numbersDistribution = new HashMap<>();

        List<Integer> numbers = ticket.getNumbers().stream()
                .filter(lottery.getWinningNumbers()::contains)
                .sorted()
                .collect(Collectors.toList());

        if (numbers.size() > 0) {
            numbersDistribution.put("pool 1", numbers);
        }

        return numbersDistribution;
    }
}
