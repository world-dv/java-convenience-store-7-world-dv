package store.service.calculator;

import store.domain.Payment;
import store.domain.Product;
import store.domain.Promotion;

public class PromotionCalculator {

    private static final Integer INIT_VALUE = 0;
    private static final Integer GET_EXTRA_FREE = -1;

    private final Product product;
    private final Promotion promotion;
    private final Integer amount;

    public PromotionCalculator(Product product, Promotion promotion, Integer amount) {
        this.product = product;
        this.promotion = promotion;
        this.amount = amount;
    }

    public Payment calculate() {
        if (amount > product.getQuantity()) {
            return calculateOverAmount();
        }
        return calculateUnderAmount();
    }

    private Payment calculateOverAmount() {
        int buyAmount = calculateAmount(product.getQuantity(), promotion.getBuy());
        int freeAmount = calculateAmount(product.getQuantity(), promotion.getGet());
        int extraAmount = product.getQuantity() - buyAmount - freeAmount;
        int morePayAmount = amount - product.getQuantity();
        product.setQuantity(product.getQuantity() - buyAmount - freeAmount);
        return new Payment(buyAmount, freeAmount, product.getPrice(), extraAmount, morePayAmount);
    }

    private Payment calculateUnderAmount() {
        int buyAmount = calculateAmount(amount, promotion.getBuy());
        int freeAmount = calculateAmount(amount, promotion.getGet());
        int extraAmount = amount - buyAmount - freeAmount;
        product.setQuantity(product.getQuantity() - buyAmount - freeAmount);
        if (canGetFree(extraAmount) && haveFreeAmount(extraAmount)) {
            product.setQuantity(product.getQuantity() - buyAmount - freeAmount - extraAmount);
            return new Payment(buyAmount + extraAmount, freeAmount, product.getPrice(), GET_EXTRA_FREE, INIT_VALUE);
        }
        return new Payment(buyAmount, freeAmount, product.getPrice(), extraAmount, INIT_VALUE);
    }

    private boolean canGetFree(Integer extraAmount) {
        return extraAmount.equals(promotion.getBuy());
    }

    private boolean haveFreeAmount(Integer extraAmount) {
        return product.getQuantity() - extraAmount >= promotion.getGet();
    }

    private Integer calculateAmount(int buyAmount, int buyOrGet) {
        return buyAmount / (promotion.getBuy() + promotion.getGet()) * buyOrGet;
    }
}
