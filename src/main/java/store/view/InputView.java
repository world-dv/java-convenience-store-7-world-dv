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
    public String readFree(String name, Integer amount) {
        while (true) {
            String input = inputFee(name, amount);
            Output.printMessage(PrintMessage.LINE_SPACE);
            if (input != null) {
                return input;
            }
        }
    }

    public String inputFee(String name, Integer amount) {
        try {
            Output.printExtraMessage(PrintMessage.INPUT_EXTRA_FREE_MESSAGE, name, amount);
            return Validation.validateInput(inputUser());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String readExtra(String name, Integer amount) {
        while (true) {
            String input = inputExtra(name, amount);
            Output.printMessage(PrintMessage.LINE_SPACE);
            if (input != null) {
                return input;
            }
        }
    }

    public String inputExtra(String name, Integer amount) {
        try {
            Output.printExtraMessage(PrintMessage.INPUT_EXTRA_PAY_MESSAGE, name, amount);
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
            Output.printMessage(PrintMessage.LINE_SPACE);
            if (input != null) {
                return input;
            }
        }
    }

    public String inputMembership() {
        try {
            Output.printMessage(PrintMessage.INPUT_MEMBERSHIP_MESSAGE);
            return Validation.validateInput(inputUser());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
