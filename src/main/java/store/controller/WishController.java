package store.controller;

import java.util.HashMap;
import java.util.List;
import store.domain.Product;
import store.domain.Wish;
import store.service.generator.ItemGenerator;
import store.service.generator.WishGenerator;
import store.service.separator.WishSeparator;

public class WishController {

    private final HashMap<String, List<Product>> products;
    private final List<ItemGenerator> itemGenerators;

    public WishController(HashMap<String, List<Product>> products, List<ItemGenerator> itemGenerators) {
        this.products = products;
        this.itemGenerators = itemGenerators;
    }

    private List<WishSeparator> makeSeparator() {
        return itemGenerators.stream()
                .map(item -> new WishSeparator(item.getItem()))
                .toList();
    }

    private List<Wish> makeWish() {
        return makeSeparator().stream()
                .map(item -> new WishGenerator(products, item.getSplitItem()).createWish())
                .toList();
    }

    public List<Wish> run() {
        return makeWish();
    }
}
