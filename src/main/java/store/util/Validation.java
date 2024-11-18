package store.util;

import store.exception.InputException;

public class Validation {

    private static final String TRUE = "Y";
    private static final String FALSE = "N";

    public static String validateInput(String input) {
        if (isNotEqualYOrN(input)) {
            throw new InputException();
        }
        return input;
    }

    private static boolean isNotEqualYOrN(String input) {
        return !(input.equals(TRUE) || input.equals(FALSE));
    }
}
