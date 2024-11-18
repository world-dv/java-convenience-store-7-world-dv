package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.util.Validation;

public class InputView implements Input {

    @Override
    public String inputUser() {
        return Console.readLine();
    }

    @Override
    public String readItem() {
        System.out.println(PrintMessage.INPUT_ITEM_MESSAGE.getMessage());
        return inputUser();
    }

    @Override
    public String readFree(String name, int amount) {
        while (true) {
            String input = inputFee(name, amount);
            System.out.println(PrintMessage.LINE_SPACE.getMessage());
            if (isNotInputNull(input)) {
                return input;
            }
        }
    }

    private boolean isNotInputNull(String input) {
        return input != null;
    }

    private String inputFee(String name, int amount) {
        try {
            System.out.printf(PrintMessage.INPUT_EXTRA_FREE_MESSAGE.getMessage(), name, amount);
            System.out.println(PrintMessage.LINE_SPACE.getMessage());
            return Validation.validateInput(inputUser());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String readExtra(String name, int amount) {
        while (true) {
            String input = inputExtra(name, amount);
            System.out.println(PrintMessage.LINE_SPACE.getMessage());
            if (isNotInputNull(input)) {
                return input;
            }
        }
    }

    private String inputExtra(String name, int amount) {
        try {
            System.out.printf(PrintMessage.INPUT_EXTRA_PAY_MESSAGE.getMessage(), name, amount);
            System.out.println(PrintMessage.LINE_SPACE);
            return Validation.validateInput(inputUser());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String readMembership() {
        while (true) {
            String input = inputMembership();
            System.out.println(PrintMessage.LINE_SPACE.getMessage());
            if (isNotInputNull(input)) {
                return input;
            }
        }
    }

    private String inputMembership() {
        try {
            System.out.println(PrintMessage.INPUT_MEMBERSHIP_MESSAGE.getMessage());
            return Validation.validateInput(inputUser());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String readRestart() {
        while (true) {
            String input = inputRestart();
            System.out.println(PrintMessage.LINE_SPACE.getMessage());
            if (isNotInputNull(input)) {
                return input;
            }
        }
    }

    private String inputRestart() {
        try {
            System.out.println(PrintMessage.INPUT_RESTART_MESSAGE.getMessage());
            return Validation.validateInput(inputUser());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
