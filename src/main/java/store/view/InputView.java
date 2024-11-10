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
        Output.printMessage(PrintMessage.INPUT_ITEM_MESSAGE);
        return inputUser();
    }

    @Override
    public String readFree(String name, int amount) {
        while (true) {
            String input = inputFee(name, amount);
            Output.printMessage(PrintMessage.LINE_SPACE);
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
            Output.printExtraMessage(PrintMessage.INPUT_EXTRA_FREE_MESSAGE, name, amount);
            return Validation.validateInput(inputUser());
        } catch (IllegalArgumentException e) {
            Output.printException(e);
        }
        return null;
    }

    @Override
    public String readExtra(String name, int amount) {
        while (true) {
            String input = inputExtra(name, amount);
            Output.printMessage(PrintMessage.LINE_SPACE);
            if (isNotInputNull(input)) {
                return input;
            }
        }
    }

    private String inputExtra(String name, int amount) {
        try {
            Output.printExtraMessage(PrintMessage.INPUT_EXTRA_PAY_MESSAGE, name, amount);
            return Validation.validateInput(inputUser());
        } catch (IllegalArgumentException e) {
            Output.printException(e);
        }
        return null;
    }

    @Override
    public String readMembership() {
        while (true) {
            String input = inputMembership();
            Output.printMessage(PrintMessage.LINE_SPACE);
            if (isNotInputNull(input)) {
                return input;
            }
        }
    }

    private String inputMembership() {
        try {
            Output.printMessage(PrintMessage.INPUT_MEMBERSHIP_MESSAGE);
            return Validation.validateInput(inputUser());
        } catch (IllegalArgumentException e) {
            Output.printException(e);
        }
        return null;
    }

    @Override
    public String readRestart() {
        while (true) {
            String input = inputRestart();
            Output.printMessage(PrintMessage.LINE_SPACE);
            if (isNotInputNull(input)) {
                return input;
            }
        }
    }

    private String inputRestart() {
        try {
            Output.printMessage(PrintMessage.INPUT_RESTART_MESSAGE);
            return Validation.validateInput(inputUser());
        } catch (IllegalArgumentException e) {
            Output.printException(e);
        }
        return null;
    }
}
