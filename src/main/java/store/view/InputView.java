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
}
