package com.github.cristibalint.lottery.javamillions.policy;

import com.github.cristibalint.lottery.javamillions.JavaMillionsLottery;

public class JavaMillionsClass3Policy extends JavaMillionsBasePolicy {

    private static final int PRIZE_AMOUNT = 10_000;
    private static final int PRIZE_CLASS = 3;

    private static final int POOL_1_MATCHES = 4;
    private static final int POOL_2_MATCHES = 1;


    public JavaMillionsClass3Policy(JavaMillionsLottery lottery) {
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
