package store.util;

public class Validation {

    public static String validateInput(String input) {
        if (isNotEqualYOrN(input)) {
            throw new IllegalArgumentException();
        }
        return input;
    }

    public static boolean isNotEqualYOrN(String input) {
        return !(input.equals("Y") || input.equals("N"));
    }
}
