package com.github.cristibalint.lottery.springlotto;

import com.github.cristibalint.lottery.Lottery;
import com.github.cristibalint.lottery.exceptions.DuplicateNumber;
import com.github.cristibalint.lottery.exceptions.NumberOutOfRange;
import com.github.cristibalint.lottery.exceptions.WrongAmountOfNumbers;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SpringLottoLotteryTest {

    @Test(expected = DuplicateNumber.class)
    public void shouldThrowDuplicateNumberException() {
        new SpringLottoLottery("1,2,3,4,5,5");
    }

    @Test(expected = NumberOutOfRange.class)
    public void shouldThrowNumberOutOfRangeExceptionOnNumbersBiggerThan36() {
        new SpringLottoLottery("1,2,3,4,5,37");
    }

    @Test(expected = NumberOutOfRange.class)
    public void shouldThrowNumberOutOfRangeExceptionOnNumbersSmallerThan1() {
        new SpringLottoLottery("0,2,3,4,5,36");
    }

    @Test(expected = WrongAmountOfNumbers.class)
    public void shouldThrowWrongAmountOfNumbersExceptionOnMoreThan6Numbers() {
        new SpringLottoLottery("1,2,3,4,5,36,4");
    }

    @Test(expected = WrongAmountOfNumbers.class)
    public void shouldThrowWrongAmountOfNumbersExceptionOnLessThan6Numbers() {
        new SpringLottoLottery("1,2,3,4,5");
    }

    @Test
    public void shouldCorrectlyClassifyWinningTicket() {
        Lottery lottery = new SpringLottoLottery("1,2,3,4,5,6");

        assertTrue(lottery.isWinner(new SpringLottoTicket("1,2,3,4,5,6")));
        assertTrue(lottery.isWinner(new SpringLottoTicket("1,2,3,4,5,7")));
        assertTrue(lottery.isWinner(new SpringLottoTicket("1,2,3,4,7,8")));
        assertTrue(lottery.isWinner(new SpringLottoTicket("1,2,4,7,8,9")));
        assertTrue(lottery.isWinner(new SpringLottoTicket("1,2,7,8,9,10")));
        assertTrue(lottery.isWinner(new SpringLottoTicket("1,7,8,9,10,11")));

        assertFalse(lottery.isWinner(new SpringLottoTicket("7,8,9,10,11,12")));
    }

    @Test
    public void shouldCorrectlyClassifyWinningPrizeClass() {
        Lottery lottery = new SpringLottoLottery("1,2,3,4,5,6");

        assertEquals(1, lottery.getPrizeClass(new SpringLottoTicket("1,2,3,4,5,6")));
        assertEquals(2, lottery.getPrizeClass(new SpringLottoTicket("1,2,3,4,5,7")));
        assertEquals(3, lottery.getPrizeClass(new SpringLottoTicket("1,2,3,4,7,8")));
        assertEquals(4, lottery.getPrizeClass(new SpringLottoTicket("1,2,4,7,8,9")));
        assertEquals(5, lottery.getPrizeClass(new SpringLottoTicket("1,2,7,8,9,10")));
        assertEquals(6, lottery.getPrizeClass(new SpringLottoTicket("1,7,8,9,10,11")));
    }

    @Test
    public void shouldCorrectlyClassifyWinningPrizeAmount() {
        Lottery lottery = new SpringLottoLottery("1,2,3,4,5,6");

        assertEquals(500_000, (long) lottery.getPrizeAmount(new SpringLottoTicket("1,2,3,4,5,6")));
        assertEquals(2000, (long) lottery.getPrizeAmount(new SpringLottoTicket("1,2,3,4,5,7")));
        assertEquals(300, (long) lottery.getPrizeAmount(new SpringLottoTicket("1,2,3,4,7,8")));
        assertEquals(50, (long) lottery.getPrizeAmount(new SpringLottoTicket("1,2,4,7,8,9")));
        assertEquals(12, (long) lottery.getPrizeAmount(new SpringLottoTicket("1,2,7,8,9,10")));
        assertEquals(3, (long) lottery.getPrizeAmount(new SpringLottoTicket("1,7,8,9,10,11")));
        assertEquals(0, (long) lottery.getPrizeAmount(new SpringLottoTicket("7,8,9,10,11,12")));
    }

    @Test
    public void shouldClasifyMatchingNumbers() {
        Lottery lottery = new SpringLottoLottery("1,2,3,4,5,6");

        Map<String, List<Integer>> distribution = lottery.getNumbersDistribution(new SpringLottoTicket("1,2,3,4,5,6"));

        assertEquals(1, distribution.size());
        assertTrue("pool 1", distribution.containsKey("pool 1"));
        assertEquals("1,2,3,4,5,6", distribution.get("pool 1").stream().map(String::valueOf).collect(Collectors.joining(",")));

        distribution = lottery.getNumbersDistribution(new SpringLottoTicket("1,7,3,8,5,6"));

        assertEquals(1, distribution.size());
        assertTrue("pool 1", distribution.containsKey("pool 1"));
        assertEquals("1,3,5,6", distribution.get("pool 1").stream().map(String::valueOf).collect(Collectors.joining(",")));
    }
}
