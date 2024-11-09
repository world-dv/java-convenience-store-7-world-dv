package store.service;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.format.DateTimeFormatter;
import store.domain.Pay;
import store.domain.Promotion;

public class DateCalculator {

    private final Promotion promotion;
    private final Pay payResult;

    public DateCalculator(Promotion promotion, Pay payResult) {
        this.promotion = promotion;
        this.payResult = payResult;
    }

    public void calculate() {
        if (isTodayNotInRange()) {
            changePromotion();
        }
    }

    private boolean isTodayNotInRange() {
        String today = DateTimes.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return isTodayBefore(today) || isTodayAfter(today);
    }

    private boolean isTodayBefore(String today) {
        return promotion.getDate().getFirst().compareTo(today) > 0;
    }

    private boolean isTodayAfter(String today) {
        return today.compareTo(promotion.getDate().getLast()) > 0;
    }

    private Integer changeExtra(int extra) {
        if (extra == -1) {
            return 0;
        }
        return extra;
    }

    private void changePromotion() {
        payResult.changeDateRange();
        payResult.addBuyAmount(payResult.getFreeAmount());
        payResult.setFreeAmount(0);
        payResult.setExtra(changeExtra(payResult.getExtra()));
    }
}
