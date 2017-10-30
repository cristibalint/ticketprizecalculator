package com.github.cristibalint.lottery.springlotto.policy;

import com.github.cristibalint.lottery.springlotto.SpringLottoLottery;

public class SpringLottoClass4Policy extends SpringLottoBasePolicy {

    private static final int PRIZE_AMOUNT = 50;
    private static final int PRIZE_CLASS = 4;

    private static final int MATCHES = 3;

    public SpringLottoClass4Policy(SpringLottoLottery lottery) {
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
