package store.service.generator;

import store.exception.TypeException;

public class ItemGenerator {

    private static final String PACKAGE_START = "[";
    private static final String PACKAGE_END = "]";
    private static final Integer PACKAGE_NOT_CONTAIN_VALUE = 1;

    private final String item;

    public ItemGenerator(String itemPackage) {
        validatePackage(itemPackage);
        validatePackageCount(itemPackage);
        this.item = removePackage(itemPackage);
    }

    private void validatePackageCount(String itemPackage) {
        if (checkPackageCount(itemPackage)) {
            throw new TypeException();
        }
    }

    private boolean checkPackageCount(String itemPackage) {
        return isNotPackageStart(itemPackage) || isNotPackageEnd(itemPackage);
    }

    private boolean isNotPackageStart(String itemPackage) {
        return itemPackage.indexOf(PACKAGE_START) != itemPackage.lastIndexOf(PACKAGE_START);
    }

    private boolean isNotPackageEnd(String itemPackage) {
        return itemPackage.indexOf(PACKAGE_END) != itemPackage.lastIndexOf(PACKAGE_END);
    }

    private void validatePackage(String itemPackage) {
        if (checkPackage(itemPackage)) {
            throw new TypeException();
        }
    }

    private boolean checkPackage(String itemPackage) {
        return !(itemPackage.startsWith(PACKAGE_START) && itemPackage.endsWith(PACKAGE_END));
    }

    private String removePackage(String itemPackage) {
        return itemPackage.substring(PACKAGE_NOT_CONTAIN_VALUE, itemPackage.length() - PACKAGE_NOT_CONTAIN_VALUE);
    }

    public String getItem() {
        return item;
    }
}
