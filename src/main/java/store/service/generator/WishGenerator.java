package store.service.generator;

import java.util.HashMap;
import java.util.List;
import store.domain.Product;
import store.domain.Wish;
import store.exception.AmountException;
import store.exception.InputException;
import store.exception.NotFoundException;

public class WishGenerator {

    private final String name;
    private final Integer amount;

    public WishGenerator(HashMap<String, List<Product>> products, List<String> item) {
        validateName(products, item.getFirst());
        this.name = item.getFirst();

        Integer amount = validateNumber(item.getLast());
        validateAmount(products, amount);
        this.amount = amount;
    }

    private void validateName(HashMap<String, List<Product>> products, String name) {
        if (!products.containsKey(name)) {
            throw new NotFoundException();
        }
    }

    private Integer calculateTotalAmount(HashMap<String, List<Product>> products) {
        List<Product> keyProducts = products.get(name);
        Integer totalAmount = 0;
        for (Product product : keyProducts) {
            totalAmount += product.getQuantity();
        }
        return totalAmount;
    }

    private void validateAmount(HashMap<String, List<Product>> products, Integer amount) {
        if (calculateTotalAmount(products) < amount) {
            throw new AmountException();
        }
    }

    private Integer validateNumber(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new InputException();
        }
    }

    public Wish createWish() {
        return new Wish(name, amount);
    }
}
