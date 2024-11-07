package store.service;

public class ItemGenerator {

    private final String item;

    public ItemGenerator(String itemPackage) {
        validatePackage(itemPackage);
        this.item = removePackage(itemPackage);
    }

    private boolean checkPackage(String itemPackage) {
        return itemPackage.charAt(0) != '[' || itemPackage.charAt(itemPackage.length() - 1) != ']';
    }

    private void validatePackage(String itemPackage) {
        if (checkPackage(itemPackage)) {
            throw new IllegalArgumentException();
        }
    }

    private String removePackage(String itemPackage) {
        return itemPackage.substring(1, itemPackage.length() - 1);
    }

    public String getItem() {
        return item;
    }
}
