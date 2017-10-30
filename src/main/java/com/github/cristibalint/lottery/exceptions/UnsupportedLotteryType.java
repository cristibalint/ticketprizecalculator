package com.github.cristibalint.lottery.exceptions;

public class UnsupportedLotteryType extends RuntimeException {

    public UnsupportedLotteryType() {
        super("lottery type is not supported!");
    }
}
