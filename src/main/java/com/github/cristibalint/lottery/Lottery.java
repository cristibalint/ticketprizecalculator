package com.github.cristibalint.lottery;

import java.util.List;
import java.util.Map;

public interface Lottery {

    List<Integer> getWinningNumbers();

    boolean isWinner(Ticket ticket);

    Integer getPrizeAmount(Ticket ticket);

    int getPrizeClass(Ticket ticket);

    Map<String,List<Integer>> getNumbersDistribution(Ticket ticket);
}
