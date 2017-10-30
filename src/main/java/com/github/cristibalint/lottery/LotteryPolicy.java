package com.github.cristibalint.lottery;

import java.util.List;
import java.util.Map;

public interface LotteryPolicy {

    boolean isSatisfiedBy(Ticket ticket);

    int getPrizeAmount();

    int getPrizeClass();

    Map<String,List<Integer>> getMatchingNumbers(Ticket ticket);
}
