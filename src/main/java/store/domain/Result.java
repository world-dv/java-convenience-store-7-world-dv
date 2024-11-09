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
}
