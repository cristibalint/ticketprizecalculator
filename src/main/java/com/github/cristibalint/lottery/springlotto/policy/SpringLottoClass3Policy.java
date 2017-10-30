package com.github.cristibalint.lottery.springlotto.policy;

import com.github.cristibalint.lottery.springlotto.SpringLottoLottery;

public class SpringLottoClass3Policy extends SpringLottoBasePolicy {

    private static final int PRIZE_AMOUNT = 300;
    private static final int PRIZE_CLASS = 3;

    private static final int MATCHES = 4;

    public SpringLottoClass3Policy(SpringLottoLottery lottery) {
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
