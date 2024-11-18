package store.util;

import java.util.List;

public class Separator {

    private static final String ITEMS_SEPARATOR = ",";
    private static final String ITEM_SEPARATOR = "-";
    private static final Integer ITEMS_SEPARATION_LIMIT = -1;
    private static final Integer ITEM_SEPARATION_LIMIT = 2;

    public static List<String> splitItems(String items) {
        return List.of(items.split(ITEMS_SEPARATOR, ITEMS_SEPARATION_LIMIT));
    }

    public static List<String> splitItem(String item) {
        return List.of(item.split(ITEM_SEPARATOR, ITEM_SEPARATION_LIMIT));
    }
}
