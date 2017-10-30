package com.github.cristibalint.lottery.javamillions;

import com.github.cristibalint.lottery.Ticket;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaMillionsTicket implements Ticket {

    private List<Integer> numbers;

    public JavaMillionsTicket(String ticketNumbers) {

        numbers = Stream.of(ticketNumbers.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        JavaMillionsLottery.validate(numbers);
    }

    @Override
    public List<Integer> getNumbers() {
        return numbers;
    }
}
