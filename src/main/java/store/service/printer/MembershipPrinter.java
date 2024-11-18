package store.service.printer;

public class MembershipPrinter {

    private static final Double DISCOUNT_RATE = 0.3;
    private static final String TRUE = "Y";
    private static final Integer MIN_DISCOUNT_MONEY = 8000;
    private static final Integer INIT_VALUE = 0;

    public Integer calculate(String input, int price) {
        if (input.equals(TRUE)) {
            return judgeMembership(calculateDiscount(price));
        }
        return INIT_VALUE;
    }

    private Integer judgeMembership(Integer discount) {
        return Math.min(discount, MIN_DISCOUNT_MONEY);
    }

    private Integer calculateDiscount(Integer total) {
        return (int) (total * DISCOUNT_RATE);
    }
}
