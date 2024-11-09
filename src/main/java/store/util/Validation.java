package store.util;

import store.exception.InputException;

public class Validation {

    public static String validateInput(String input) {
        if (isNotEqualYOrN(input)) {
            throw new InputException();
        }
        return input;
    }

    public static boolean isNotEqualYOrN(String input) {
        return !(input.equals("Y") || input.equals("N"));
    }
}
