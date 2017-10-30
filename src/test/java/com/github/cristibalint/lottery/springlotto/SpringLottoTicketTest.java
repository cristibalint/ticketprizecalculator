package com.github.cristibalint.lottery.springlotto;

import com.github.cristibalint.lottery.exceptions.DuplicateNumber;
import com.github.cristibalint.lottery.exceptions.NumberOutOfRange;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class SpringLottoTicketTest {

    @Test(expected = DuplicateNumber.class)
    public void shouldThrowDuplicateNumberException() {
        new SpringLottoTicket("1,2,3,4,5,5");
    }

    @Test(expected = NumberOutOfRange.class)
    public void shouldThrowNumberOutOfRangeExceptionOnNumbersBiggerThan36() {
        new SpringLottoTicket("1,2,3,4,5,37");
    }

    @Test(expected = NumberOutOfRange.class)
    public void shouldThrowNumberOutOfRangeExceptionOnNumbersSmallerThan1() {
        new SpringLottoTicket("0,2,3,4,5,36");
    }

    @Test
    public void shouldReturnSameNumbers() {
        final List<Integer> numbers = new SpringLottoTicket("1,2,3,4,5,6").getNumbers();

        assertTrue(numbers.contains(1));
        assertTrue(numbers.contains(2));
        assertTrue(numbers.contains(3));
        assertTrue(numbers.contains(4));
        assertTrue(numbers.contains(5));
        assertTrue(numbers.contains(6));
    }
}
