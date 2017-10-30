package com.github.cristibalint.lottery.springlotto.policy;

import com.github.cristibalint.lottery.Ticket;
import com.github.cristibalint.lottery.springlotto.SpringLottoLottery;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SpringLottoClass0Policy extends SpringLottoBasePolicy {

    public SpringLottoClass0Policy(SpringLottoLottery lottery) {
        super(lottery);
    }

    @Override
    public boolean isSatisfiedBy(Ticket ticket) {
        return true;
    }

    @Override
    public int getPrizeAmount() {
        return 0;
    }

    @Override
    public int getPrizeClass() {
        return 0;
    }

    @Override
    protected int getMatches() {
        return 0;
    }

    @Override
    public Map<String, List<Integer>> getMatchingNumbers(Ticket ticket) {
        return Collections.emptyMap();
    }
}
