package store.util;

import java.util.List;

public class Separator {

    public static List<String> splitItems(String items) {
        return List.of(items.split(","));
    }

    public static List<String> splitItem(String item) {
        return List.of(item.split("-", 2));
    }
}
