package store.view;

import camp.nextstep.edu.missionutils.Console;

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
        Output.printExtraMessage(PrintMessage.INPUT_EXTRA_FREE_MESSAGE, name, amount);
        return inputUser();
    }

    @Override
    public String readExtra(String name, Integer amount) {
        Output.printExtraMessage(PrintMessage.INPUT_EXTRA_PAY_MESSAGE, name, amount);
        return inputUser();
    }
}
