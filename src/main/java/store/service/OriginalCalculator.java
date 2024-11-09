package store.service;

import store.domain.Pay;
import store.domain.Product;

public class OriginalCalculator {

    private final Product product;
    private final Integer amount;

    public OriginalCalculator(Product product, Integer amount) {
        validate(product, amount);
        this.product = product;
        this.amount = amount;
    }

    private void validate(Product product, Integer amount) {
        if (product.getQuantity() < amount) {
            throw new IllegalArgumentException();
        }
    }

    private void updateQuantity() {
        product.setQuantity(product.getQuantity() - amount);
    }

    public Pay calculate() {
        updateQuantity();
        return new Pay(amount, 0, product.getPrice(), 0);
    }
}
