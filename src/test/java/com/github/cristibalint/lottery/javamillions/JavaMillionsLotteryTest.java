package com.github.cristibalint.lottery.javamillions;

import com.github.cristibalint.lottery.Lottery;
import com.github.cristibalint.lottery.exceptions.DuplicateNumber;
import com.github.cristibalint.lottery.exceptions.NumberOutOfRange;
import com.github.cristibalint.lottery.exceptions.WrongAmountOfNumbers;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class JavaMillionsLotteryTest {

    @Test(expected = DuplicateNumber.class)
    public void shouldThrowDuplicateNumberException() {
        new JavaMillionsLottery("1,1,3,4,5,5");
    }

    @Test(expected = NumberOutOfRange.class)
    public void shouldThrowNumberOutOfRangeExceptionOnNumbersBiggerThan49() {
        new JavaMillionsLottery("1,2,3,4,5,54");
    }

    @Test(expected = NumberOutOfRange.class)
    public void shouldThrowNumberOutOfRangeExceptionOnNumbersSmallerThan1() {
        new JavaMillionsLottery("0,2,3,42,5,36");
    }

    @Test(expected = WrongAmountOfNumbers.class)
    public void shouldThrowWrongAmountOfNumbersExceptionOnMoreThan6Numbers() {
        new JavaMillionsLottery("11,23,31,4,5,36,4");
    }

    @Test(expected = WrongAmountOfNumbers.class)
    public void shouldThrowWrongAmountOfNumbersExceptionOnLessThan6Numbers() {
        new JavaMillionsLottery("7,12,43,4,5");
    }

    @Test
    public void shouldCorrectlyClassifyWinningTicket() {
        Lottery lottery = new JavaMillionsLottery("1,2,3,4,5,6");

        assertTrue(lottery.isWinner(new JavaMillionsTicket("1,2,3,4,5,6")));
        assertTrue(lottery.isWinner(new JavaMillionsTicket("1,2,3,4,5,7")));
        assertTrue(lottery.isWinner(new JavaMillionsTicket("1,2,3,4,7,6")));
        assertTrue(lottery.isWinner(new JavaMillionsTicket("1,2,3,4,7,8")));
        assertTrue(lottery.isWinner(new JavaMillionsTicket("1,2,3,7,8,6")));
        assertTrue(lottery.isWinner(new JavaMillionsTicket("1,2,3,7,8,9")));
        assertTrue(lottery.isWinner(new JavaMillionsTicket("1,2,7,8,9,6")));
        assertTrue(lottery.isWinner(new JavaMillionsTicket("1,2,7,8,9,1")));

        assertFalse(lottery.isWinner(new JavaMillionsTicket("7,8,9,10,11,1")));
    }

    @Test
    public void shouldCorrectlyClassifyWinningPrizeClass() {
        Lottery lottery = new JavaMillionsLottery("1,2,3,4,5,6");

        assertEquals(1, lottery.getPrizeClass(new JavaMillionsTicket("1,2,3,4,5,6")));
        assertEquals(2, lottery.getPrizeClass(new JavaMillionsTicket("1,2,3,4,5,7")));
        assertEquals(3, lottery.getPrizeClass(new JavaMillionsTicket("1,2,3,4,7,6")));
        assertEquals(4, lottery.getPrizeClass(new JavaMillionsTicket("1,2,3,4,7,8")));
        assertEquals(5, lottery.getPrizeClass(new JavaMillionsTicket("1,2,3,7,8,6")));
        assertEquals(6, lottery.getPrizeClass(new JavaMillionsTicket("1,2,3,7,8,9")));
        assertEquals(7, lottery.getPrizeClass(new JavaMillionsTicket("1,2,7,8,9,6")));
        assertEquals(8, lottery.getPrizeClass(new JavaMillionsTicket("1,2,7,8,9,1")));
    }

    @Test
    public void shouldCorrectlyClassifyWinningPrizeAmount() {
        Lottery lottery = new JavaMillionsLottery("1,2,3,4,5,6");

        assertEquals(10_000_000, (long) lottery.getPrizeAmount(new JavaMillionsTicket("1,2,3,4,5,6")));
        assertEquals(100_000, (long) lottery.getPrizeAmount(new JavaMillionsTicket("1,2,3,4,5,7")));
        assertEquals(10_000, (long) lottery.getPrizeAmount(new JavaMillionsTicket("1,2,3,4,7,6")));
        assertEquals(500, (long) lottery.getPrizeAmount(new JavaMillionsTicket("1,2,3,4,7,8")));
        assertEquals(100, (long) lottery.getPrizeAmount(new JavaMillionsTicket("1,2,3,7,8,6")));
        assertEquals(30, (long) lottery.getPrizeAmount(new JavaMillionsTicket("1,2,3,7,8,9")));
        assertEquals(5, (long) lottery.getPrizeAmount(new JavaMillionsTicket("1,2,7,8,9,6")));
        assertEquals(2, (long) lottery.getPrizeAmount(new JavaMillionsTicket("1,2,7,8,9,1")));
        assertEquals(0, (long) lottery.getPrizeAmount(new JavaMillionsTicket("1,7,8,9,10,1")));
    }

    @Test
    public void shouldClasifyMatchingNumbers() {
        Lottery lottery = new JavaMillionsLottery("1,2,3,4,5,6");

        Map<String, List<Integer>> distribution = lottery.getNumbersDistribution(new JavaMillionsTicket("1,2,3,4,5,6"));

        assertEquals(2, distribution.size());
        assertTrue(distribution.containsKey("pool 1"));
        assertTrue(distribution.containsKey("pool 2"));
        assertEquals("1,2,3,4,5", distribution.get("pool 1").stream().map(String::valueOf).collect(Collectors.joining(",")));
        assertEquals("6", distribution.get("pool 2").stream().map(String::valueOf).collect(Collectors.joining(",")));

        distribution = lottery.getNumbersDistribution(new JavaMillionsTicket("1,7,3,8,5,6"));

        assertEquals(2, distribution.size());
        assertTrue(distribution.containsKey("pool 1"));
        assertTrue(distribution.containsKey("pool 2"));
        assertEquals("1,3,5", distribution.get("pool 1").stream().map(String::valueOf).collect(Collectors.joining(",")));
        assertEquals("6", distribution.get("pool 2").stream().map(String::valueOf).collect(Collectors.joining(",")));
    }
}
