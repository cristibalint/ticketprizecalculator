package com.github.cristibalint.lottery.springlotto;

import com.github.cristibalint.lottery.Ticket;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpringLottoTicket implements Ticket {

    private List<Integer> numbers;

    public SpringLottoTicket(String ticketNumbers) {

        numbers = Stream.of(ticketNumbers.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        SpringLottoLottery.validate(numbers);
    }

    @Override
    public List<Integer> getNumbers() {
        return numbers;
    }
}
