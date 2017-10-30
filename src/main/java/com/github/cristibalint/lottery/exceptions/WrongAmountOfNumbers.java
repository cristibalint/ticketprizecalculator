package com.github.cristibalint.lottery.exceptions;

public class WrongAmountOfNumbers extends RuntimeException {

    public WrongAmountOfNumbers() {
        super("wrong amount of numbers provided!");
    }
}
