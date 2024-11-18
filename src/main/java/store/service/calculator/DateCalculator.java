package store.service.calculator;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.format.DateTimeFormatter;
import store.domain.Promotion;

public class DateCalculator {

    private static final Integer JUDGE_POSITIVE_NUMBER = 0;
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private final Promotion promotion;

    public DateCalculator(Promotion promotion) {
        this.promotion = promotion;
    }

    public boolean calculate() {
        return isTodayNotInRange();
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
