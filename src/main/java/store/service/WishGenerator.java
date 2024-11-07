package store.service;

import java.util.List;
import store.domain.Wish;

public class WishGenerator {

    private final String name;
    private final Integer amount;

    public WishGenerator(List<String> item) {
        this.name = item.getFirst();
        this.amount = validateNumber(item.getLast());
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
