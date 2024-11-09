package store.controller;

import java.util.List;
import store.service.generator.ItemGenerator;
import store.util.Separator;
import store.view.InputView;
import store.view.OutputView;
import store.view.PrintMessage;

public class ItemController {

    private final InputView inputView;
    private final OutputView outputView;

    public ItemController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    private List<ItemGenerator> makeGenerator(String items) {
        return Separator.splitItems(items).stream()
                .map(ItemGenerator::new)
                .toList();
    }

    private List<ItemGenerator> inputItems() {
        try {
            String items = inputView.readItem();
            return makeGenerator(items);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<ItemGenerator> run() {
        while (true) {
            List<ItemGenerator> input = inputItems();
            outputView.printlnMessage(PrintMessage.LINE_SPACE);
            if (input != null) {
                return input;
            }
        }
    }
}
