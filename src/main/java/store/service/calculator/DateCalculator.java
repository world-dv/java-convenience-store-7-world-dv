package store.service.calculator;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.format.DateTimeFormatter;
import store.domain.Payment;
import store.domain.Promotion;

public class DateCalculator {

    private static final Integer JUDGE_POSITIVE_NUMBER = 0;
    private static final Integer INIT_VALUE = 0;
    private static final Integer GET_EXTRA_FREE = -1;
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private final Promotion promotion;
    private final Payment payResult;

    public DateCalculator(Promotion promotion, Payment payResult) {
        this.promotion = promotion;
        this.payResult = payResult;
    }

    public void calculate() {
        if (isTodayNotInRange()) {
            changePromotion();
        }
    }

    private void changePromotion() {
        payResult.changeDateRange();
        payResult.addBuyAmount(payResult.getFreeAmount());
        payResult.setFreeAmount(INIT_VALUE);
        payResult.setExtraAmount(changeExtra(payResult.getExtraAmount()));
    }

    private Integer changeExtra(Integer extra) {
        if (extra.equals(GET_EXTRA_FREE)) {
            return INIT_VALUE;
        }
        return extra;
    }

    private boolean isTodayNotInRange() {
        String today = getTodayDate();
        return isTodayBefore(today) || isTodayAfter(today);
    }

    private boolean isTodayBefore(String today) {
        return promotion.getStartDate().compareTo(today) > JUDGE_POSITIVE_NUMBER;
    }

    private boolean isTodayAfter(String today) {
        return today.compareTo(promotion.getEndDate()) > JUDGE_POSITIVE_NUMBER;
    }

    private String getTodayDate() {
        return DateTimes.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
