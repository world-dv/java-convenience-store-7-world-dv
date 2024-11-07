package store.service;

import java.util.List;
import store.util.Separator;

public class ItemSeparator {

    private final List<String> splitItem;

    public ItemSeparator(String item) {
        validate(item);
        this.splitItem = Separator.splitItem(item);
    }

    private boolean checkNoSeparator(String item) {
        return !item.contains("-");
    }

    private void validate(String item) {
        if (checkNoSeparator(item)) {
            throw new IllegalArgumentException();
        }
    }

    public List<String> getSplitItem() {
        return splitItem;
    }
}
