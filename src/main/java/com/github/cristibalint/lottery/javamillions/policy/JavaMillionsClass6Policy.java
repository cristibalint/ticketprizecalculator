package com.github.cristibalint.lottery.javamillions.policy;

import com.github.cristibalint.lottery.javamillions.JavaMillionsLottery;

public class JavaMillionsClass6Policy extends JavaMillionsBasePolicy {

    private static final int PRIZE_AMOUNT = 30;
    private static final int PRIZE_CLASS = 6;

    private static final int POOL_1_MATCHES = 3;
    private static final int POOL_2_MATCHES = 0;

    public JavaMillionsClass6Policy(JavaMillionsLottery lottery) {
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
    protected int getPool1Matches() {
        return POOL_1_MATCHES;
    }

    @Override
    protected int getPool2Matches() {
        return POOL_2_MATCHES;
    }
}
