package store.service;

public class ItemGenerator {

    private final String item;

    public ItemGenerator(String itemPackage) {
        validatePackage(itemPackage);
        validatePackageCount(itemPackage);
        this.item = removePackage(itemPackage);
    }

    private boolean checkPackageCount(String itemPackage) {
        return itemPackage.indexOf("[") != itemPackage.lastIndexOf("[") || itemPackage.indexOf("]") != itemPackage.lastIndexOf("]");
    }

    private void validatePackageCount(String itemPackage) {
        if (checkPackageCount(itemPackage)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean checkPackage(String itemPackage) {
        return !(itemPackage.startsWith("[") && itemPackage.endsWith("]"));
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
