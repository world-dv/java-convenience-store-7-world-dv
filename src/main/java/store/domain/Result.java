package store.domain;

public class Result {

    private final String name;
    private final Pay originalResult;
    private final Pay promotionResult;

    public Result(String name, Pay originalResult, Pay promotionResult) {
        this.name = name;
        this.originalResult = originalResult;
        this.promotionResult = promotionResult;
    }

    public String getName() {
        return name;
    }

    public Pay getOriginalResult() {
        return originalResult;
    }

    public Pay getPromotionResult() {
        return promotionResult;
    }

    public Integer calculateTotalAmount() {
        Integer totalAmount = 0;
        if (originalResult != null) {
            totalAmount += originalResult.getTotalAmount();
        }
        if (promotionResult != null) {
            totalAmount += promotionResult.getTotalAmount();
        }
        return totalAmount;
    }

    public Integer calculateTotalPrice() {
        Integer totalPrice = 0;
        if (originalResult != null) {
            totalPrice += originalResult.getTotalPrice();
        }
        if (promotionResult != null) {
            totalPrice += promotionResult.getTotalPrice();
        }
        return totalPrice;
    }

    public Integer calculateFreeAmount() {
        if (promotionResult != null) {
            return promotionResult.getFreeAmount();
        }
        return 0;
    }

    public Integer calculateFreePrice() {
        if (promotionResult != null) {
            return promotionResult.getFreeAmount() * promotionResult.getPrice();
        }
        return 0;
    }
}
