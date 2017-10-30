package com.github.cristibalint.lottery;

import com.github.cristibalint.lottery.exceptions.UnsupportedLotteryType;
import com.github.cristibalint.lottery.exceptions.UnsupportedTicketType;
import com.github.cristibalint.lottery.javamillions.JavaMillionsLottery;
import com.github.cristibalint.lottery.javamillions.JavaMillionsTicket;
import com.github.cristibalint.lottery.springlotto.SpringLottoLottery;
import com.github.cristibalint.lottery.springlotto.SpringLottoTicket;

public class LotteryFactory {

    public static final String JAVA_MILLIONS_LOTTERY = "JavaMillions";
    public static final String SPRING_LOTTO_LOTTERY = "SpringLotto";

    public static Lottery createLottery(String lotteryType, String winningNumbers) {
        switch (lotteryType) {
            case JAVA_MILLIONS_LOTTERY:
                return new JavaMillionsLottery(winningNumbers);

            case SPRING_LOTTO_LOTTERY:
                return new SpringLottoLottery(winningNumbers);

            default:
                throw new UnsupportedLotteryType();
        }
    }

    public static Ticket createTicket(String lotteryType, String ticketNumbers) {
        switch (lotteryType) {
            case LotteryFactory.JAVA_MILLIONS_LOTTERY:
                return new JavaMillionsTicket(ticketNumbers);

            case LotteryFactory.SPRING_LOTTO_LOTTERY:
                return new SpringLottoTicket(ticketNumbers);

            default:
                throw new UnsupportedTicketType();
        }
    }
}
