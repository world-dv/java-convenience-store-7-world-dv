package store.service.separator;

import java.util.List;
import store.exception.TypeException;
import store.util.Separator;

public class WishSeparator {

    private static final String ITEM_SEPARATOR = "-";

    private final List<String> splitWish;

    public WishSeparator(String item) {
        validate(item);
        this.splitWish = Separator.splitItem(item);
    }

    private boolean checkNoSeparator(String item) {
        return !item.contains(ITEM_SEPARATOR);
    }

    private boolean checkSeparatorNumber(String item) {
        return !(item.indexOf(ITEM_SEPARATOR) == item.lastIndexOf(ITEM_SEPARATOR));
    }

    private void validate(String item) {
        if (checkNoSeparator(item) || checkSeparatorNumber(item)) {
            throw new TypeException();
        }
    }

    public List<String> getSplitItem() {
        return splitWish;
    }
}
