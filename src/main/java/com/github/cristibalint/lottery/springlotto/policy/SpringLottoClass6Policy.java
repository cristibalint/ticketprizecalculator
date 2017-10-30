package com.github.cristibalint.lottery.springlotto.policy;

import com.github.cristibalint.lottery.springlotto.SpringLottoLottery;

public class SpringLottoClass6Policy extends SpringLottoBasePolicy {

    private static final int PRIZE_AMOUNT = 3;
    private static final int PRIZE_CLASS = 6;

    private static final int MATCHES = 1;

    public SpringLottoClass6Policy(SpringLottoLottery lottery) {
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
