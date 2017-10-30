package com.github.cristibalint.lottery.exceptions;

public class DuplicateNumber extends RuntimeException {
    public DuplicateNumber(Integer number) {
        super("duplicate number found: " + number);
    }
}
