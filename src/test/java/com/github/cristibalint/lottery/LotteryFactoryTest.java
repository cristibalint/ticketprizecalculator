package com.github.cristibalint.lottery;

import com.github.cristibalint.lottery.exceptions.UnsupportedLotteryType;
import com.github.cristibalint.lottery.exceptions.UnsupportedTicketType;
import com.github.cristibalint.lottery.javamillions.JavaMillionsLottery;
import com.github.cristibalint.lottery.javamillions.JavaMillionsTicket;
import com.github.cristibalint.lottery.springlotto.SpringLottoLottery;
import com.github.cristibalint.lottery.springlotto.SpringLottoTicket;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LotteryFactoryTest {

    @Test
    public void shouldReturnJavaMillionsLottery() {
        final Class<? extends Lottery> lottery = LotteryFactory.createLottery("JavaMillions", "1,2,3,4,5,6").getClass();

        assertTrue(lottery == JavaMillionsLottery.class);
    }


    @Test
    public void shouldReturnSpringLottoLottery() {

        final Class<? extends Lottery> lottery = LotteryFactory.createLottery("SpringLotto", "1,2,3,4,5,6").getClass();

        assertTrue(lottery == SpringLottoLottery.class);
    }

    @Test(expected = UnsupportedLotteryType.class)
    public void shouldThrowUnsupportedLotteryTypeException() {

        LotteryFactory.createLottery("UnsupportedLotto", "1,2,3,4,5,6");
    }

    @Test
    public void shouldReturnJavaMillionsTicket() {
        final Class<? extends Ticket> ticket = LotteryFactory.createTicket("JavaMillions", "1,2,3,4,5,6").getClass();

        assertTrue(ticket == JavaMillionsTicket.class);
    }


    @Test
    public void shouldReturnSpringLottoTicket() {

        final Class<? extends Ticket> ticket = LotteryFactory.createTicket("SpringLotto", "1,2,3,4,5,6").getClass();

        assertTrue(ticket == SpringLottoTicket.class);
    }

    @Test(expected = UnsupportedTicketType.class)
    public void shouldThrowUnsupportedTicketTypeException() {

        LotteryFactory.createTicket("UnsupportedLotto", "1,2,3,4,5,6");
    }
}
