package store.domain;

public class Result {

    private static final Integer INIT_VALUE = 0;

    private final String name;
    private final Payment originalResult;
    private final Payment promotionResult;

    public Result(String name, Payment originalResult, Payment promotionResult) {
        this.name = name;
        this.originalResult = originalResult;
        this.promotionResult = promotionResult;
    }

    public Integer calculateTotalAmount() {
        int total = INIT_VALUE;
        if (originalResult != null) {
            total += originalResult.sumTotalAmount();
        }
        if (promotionResult != null) {
            total += promotionResult.sumTotalAmount();
        }
        return total;
    }

    public Integer calculateTotalPrice() {
        int total = INIT_VALUE;
        if (originalResult != null) {
            total += originalResult.calculateTotalPrice();
        }
        if (promotionResult != null) {
            total += promotionResult.calculateTotalPrice();
        }
        return total;
    }

    public Integer calculateFreeAmount() {
        if (promotionResult != null) {
            return promotionResult.getFreeAmount();
        }
        return INIT_VALUE;
    }

    public Integer calculateFreePrice() {
        if (promotionResult != null) {
            return promotionResult.getPrice() * promotionResult.getFreeAmount();
        }
        return INIT_VALUE;
    }

    public Integer calculateMembership() {
        int total = INIT_VALUE;
        if (originalResult != null) {
            total += originalResult.calculateTotalPrice();
        }
        if (promotionResult != null) {
            total += calculatePromotionMembership();
        }
        return total;
    }

    private Integer calculatePromotionMembership() {
        int total = INIT_VALUE;
        if (promotionResult.getDateRange()) {
            total += promotionResult.calculateTotalPrice();
        }
        if (promotionResult.getMembership()) {
            total += promotionResult.getExtraPrice();
        }
        return total;
    }

    public String getName() {
        return name;
    }
}
