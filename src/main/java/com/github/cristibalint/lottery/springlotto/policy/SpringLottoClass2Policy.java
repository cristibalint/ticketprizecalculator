package com.github.cristibalint.lottery.springlotto.policy;

import com.github.cristibalint.lottery.springlotto.SpringLottoLottery;

public class SpringLottoClass2Policy extends SpringLottoBasePolicy {

    private static final int PRIZE_AMOUNT = 2_000;
    private static final int PRIZE_CLASS = 2;

    private static final int MATCHES = 5;

    public SpringLottoClass2Policy(SpringLottoLottery lottery) {
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
