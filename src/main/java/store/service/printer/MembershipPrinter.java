package store.service.printer;

import java.util.List;
import store.domain.Result;

public class MembershipPrinter {

    private static final Double DISCOUNT_RATE = 0.3;
    private static final String TRUE = "Y";
    private static final Integer MIN_DISCOUNT_MONEY = 8000;
    private static final Integer INIT_VALUE = 0;

    private final List<Result> results;

    public MembershipPrinter(List<Result> results) {
        this.results = results;
    }

    public Integer calculate(String input) {
        if (input.equals(TRUE)) {
            return judgeMembership(calculateMembership());
        }
        return INIT_VALUE;
    }

    private Integer calculateMembership() {
        int total = 0;
        for (Result result : results) {
            total += result.calculateMembership();
        }
        return calculateDiscount(total);
    }

    private Integer judgeMembership(Integer discount) {
        return Math.min(discount, MIN_DISCOUNT_MONEY);
    }

    private Integer calculateDiscount(Integer total) {
        return (int) (total * DISCOUNT_RATE);
    }
}
