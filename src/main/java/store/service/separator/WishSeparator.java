package store.service.separator;

import java.util.List;
import store.util.Separator;

public class WishSeparator {

    private final List<String> splitWish;

    public WishSeparator(String item) {
        validate(item);
        this.splitWish = Separator.splitItem(item);
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
        return splitWish;
    }
}
