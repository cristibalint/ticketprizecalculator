package com.github.cristibalint.lottery.javamillions;

import com.github.cristibalint.lottery.exceptions.DuplicateNumber;
import com.github.cristibalint.lottery.exceptions.NumberOutOfRange;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class JavaMillionsTicketTest {

    @Test(expected = DuplicateNumber.class)
    public void shouldThrowDuplicateNumberException() {
        new JavaMillionsTicket("1,2,3,3,5,5");
    }

    @Test(expected = NumberOutOfRange.class)
    public void shouldThrowNumberOutOfRangeExceptionOnNumbersBiggerThan49() {
        new JavaMillionsTicket("1,2,3,4,5,50");
    }

    @Test(expected = NumberOutOfRange.class)
    public void shouldThrowNumberOutOfRangeExceptionOnNumbersSmallerThan1() {
        new JavaMillionsTicket("0,2,3,23,5,36");
    }

    @Test
    public void shouldReturnSameNumbers() {
        final List<Integer> numbers = new JavaMillionsTicket("1,2,3,4,5,6").getNumbers();

        assertTrue(numbers.contains(1));
        assertTrue(numbers.contains(2));
        assertTrue(numbers.contains(3));
        assertTrue(numbers.contains(4));
        assertTrue(numbers.contains(5));
        assertTrue(numbers.contains(6));
    }
}
