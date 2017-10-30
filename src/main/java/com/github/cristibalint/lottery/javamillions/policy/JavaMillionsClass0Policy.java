package com.github.cristibalint.lottery.javamillions.policy;

import com.github.cristibalint.lottery.Ticket;
import com.github.cristibalint.lottery.javamillions.JavaMillionsLottery;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JavaMillionsClass0Policy extends JavaMillionsBasePolicy {

    public JavaMillionsClass0Policy(JavaMillionsLottery lottery) {
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
    protected int getPool1Matches() {
        return 0;
    }

    @Override
    protected int getPool2Matches() {
        return 0;
    }

    @Override
    public Map<String, List<Integer>> getMatchingNumbers(Ticket ticket) {
        return Collections.emptyMap();
    }
}
