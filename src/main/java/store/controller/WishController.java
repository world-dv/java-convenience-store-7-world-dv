package store.controller;

import java.util.HashMap;
import java.util.List;
import store.domain.Product;
import store.domain.Wish;
import store.service.generator.ItemGenerator;
import store.service.generator.WishGenerator;
import store.service.separator.WishSeparator;
import store.util.Separator;
import store.view.InputView;
import store.view.Output;
import store.view.OutputView;
import store.view.PrintMessage;

public class WishController {

    private final InputView inputView;
    private final OutputView outputView;
    private final HashMap<String, List<Product>> products;

    public WishController(InputView inputView, OutputView outputView, HashMap<String, List<Product>> products) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.products = products;
    }

    private List<ItemGenerator> makeGenerator(String items) {
        return Separator.splitItems(items).stream()
                .map(ItemGenerator::new)
                .toList();
    }

    private List<WishSeparator> makeSeparator(String items) {
        return makeGenerator(items).stream()
                .map(item -> new WishSeparator(item.getItem()))
                .toList();
    }

    private List<Wish> makeWish(String items) {
        return makeSeparator(items).stream()
                .map(item -> new WishGenerator(products, item.getSplitItem()).createWish())
                .toList();
    }

    private List<Wish> inputWish() {
        try {
            String items = inputView.readItem();
            return makeWish(items);
        } catch (IllegalArgumentException e) {
            Output.printException(e);
        }
        return null;
    }

    public List<Wish> run() {
        while (true) {
            List<Wish> input = inputWish();
            outputView.printlnMessage(PrintMessage.LINE_SPACE);
            if (input != null) {
                return input;
            }
        }
    }
}
