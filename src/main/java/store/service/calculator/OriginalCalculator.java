package store.service.calculator;

import store.domain.Payment;
import store.domain.Product;

public class OriginalCalculator {

    private static final Integer INIT_VALUE = 0;

    private final Product product;
    private final Integer amount;

    public OriginalCalculator(Product product, Integer amount) {
        this.product = product;
        this.amount = amount;
    }

    public Payment calculate() {
        if (product.getQuantity() < amount) {
            int extra = amount - product.getQuantity();
            product.setQuantity(0);
            return new Payment(amount, INIT_VALUE, product.getPrice(), extra, INIT_VALUE);
        }
        updateQuantity();
        return createPayment();
    }

    private void updateQuantity() {
        product.setQuantity(product.getQuantity() - amount);
    }

    private Payment createPayment() {
        return new Payment(amount, INIT_VALUE, product.getPrice(), INIT_VALUE, INIT_VALUE);
    }
}
