package store.controller;

import java.util.List;
import store.service.generator.ItemGenerator;
import store.util.Separator;
import store.view.InputView;

public class ItemController {

    private final InputView inputView;

    public ItemController(InputView inputView) {
        this.inputView = inputView;
    }

    private List<ItemGenerator> makeGenerator(String items) {
        return Separator.splitItems(items).stream()
                .map(ItemGenerator::new)
                .toList();
    }

    public List<ItemGenerator> run() {
        String items = inputView.readItem();
        return makeGenerator(items);
    }
}
