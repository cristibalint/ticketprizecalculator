package com.github.cristibalint.lottery.springlotto.policy;

import com.github.cristibalint.lottery.springlotto.SpringLottoLottery;

public class SpringLottoClass5Policy extends SpringLottoBasePolicy {

    private static final int PRIZE_AMOUNT = 12;
    private static final int PRIZE_CLASS = 5;

    private static final int MATCHES = 2;

    public SpringLottoClass5Policy(SpringLottoLottery lottery) {
        super(lottery);
    }

    @Override
    public int getPrizeAmount() {
        return PRIZE_AMOUNT;
    }

    @Override
    public int getPrizeClass() {
        return PRIZE_CLASS;
    }

    @Override
    protected int getMatches() {
        return MATCHES;
    }
}
