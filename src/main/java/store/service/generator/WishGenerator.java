package store.service.generator;

import java.util.HashMap;
import java.util.List;
import store.domain.Product;
import store.domain.Wish;

public class WishGenerator {

    private final String name;
    private final Integer amount;

    public WishGenerator(HashMap<String, List<Product>> products, List<String> item) {
        validateName(products, item.getFirst());
        this.name = item.getFirst();
        this.amount = validateNumber(item.getLast());
    }

    private void validateName(HashMap<String, List<Product>> products, String name) {
        if (!products.containsKey(name)) {
            throw new IllegalArgumentException();
        }
    }

    private Integer validateNumber(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public Wish createWish() {
        return new Wish(name, amount);
    }
}
