package com.github.cristibalint.lottery.exceptions;

public class NumberOutOfRange extends RuntimeException {

    public NumberOutOfRange(Integer number, int lowLimit, int highLimit) {
        super("'" + number + "' is out of range [ " + lowLimit + " : " + highLimit + " ]");
    }
}
