package store.service;

import store.domain.Pay;
import store.domain.Product;
import store.domain.Promotion;

public class PromotionCalculator {

    private final Product product;
    private final Promotion promotion;
    private final Integer amount;

    public PromotionCalculator(Product product, Promotion promotion, Integer amount) {
        this.product = product;
        this.promotion = promotion;
        this.amount = amount;
    }

    public Pay calculate() {
        int buy = promotion.getContent().getFirst();
        int get = promotion.getContent().getLast();

        if (product.getQuantity() >= amount) {
            int buyAmount = calculateAmount(amount, buy);
            int freeAmount = calculateAmount(amount, get);
            int extraAmount = amount - buyAmount - freeAmount;
            product.setQuantity(product.getQuantity() - buyAmount - freeAmount - extraAmount);

            if (extraAmount == buy && product.getQuantity() >= get) {
                return new Pay(buyAmount + extraAmount, freeAmount, product.getPrice(), -1);
            }
            return new Pay(buyAmount + extraAmount, freeAmount, product.getPrice(), 0);
        }
        int buyAmount = calculateAmount(product.getQuantity(), buy);
        int freeAmount = calculateAmount(product.getQuantity(), get);
        int extraAmount = product.getQuantity() - buyAmount - freeAmount;
        int extra = amount - product.getQuantity();
        product.setQuantity(0);
        return new Pay(buyAmount + extraAmount, freeAmount, product.getPrice(), extra);
    }

    private Integer calculateAmount(int buyAmount, int buyOrGet) {
        return buyAmount / (promotion.getContent().getFirst() + promotion.getContent().getLast()) * buyOrGet;
    }
}
