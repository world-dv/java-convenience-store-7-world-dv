package store.service.calculator;

import store.domain.Payment;
import store.domain.Product;
import store.exception.AmountException;

public class OriginalCalculator {

    private final Product product;
    private final Integer amount;

    public OriginalCalculator(Product product, Integer amount) {
        this.product = product;
        this.amount = amount;
    }

    private void updateQuantity() {
        product.setQuantity(product.getQuantity() - amount);
    }

    public Payment calculate() {
        updateQuantity();
        return new Payment(amount, 0, product.getPrice(), 0);
    }
}
