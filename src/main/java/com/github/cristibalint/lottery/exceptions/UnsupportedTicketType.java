package com.github.cristibalint.lottery.exceptions;

public class UnsupportedTicketType extends RuntimeException {

    public UnsupportedTicketType() {
        super("ticket type is not supported!");
    }
}
