package store.controller;

import java.util.List;
import store.domain.Result;
import store.service.printer.FreePrinter;
import store.service.printer.TotalPrinter;
import store.view.OutputView;
import store.view.PrintMessage;

public class RecipeController {

    private final OutputView outputView;
    private final List<Result> results;

    public RecipeController(OutputView outputView, List<Result> results) {
        this.outputView = outputView;
        this.results = results;
    }

    public void print() {
        outputView.printlnMessage(PrintMessage.LINE_CONVENIENCE_STORE);
        TotalPrinter totalPrinter = new TotalPrinter(results);
        outputView.printTotal(totalPrinter.calculate());

        outputView.printlnMessage(PrintMessage.LINE_FREE);
        FreePrinter freePrinter = new FreePrinter(results);
        outputView.printFree(freePrinter.calculate());

        outputView.printlnMessage(PrintMessage.LINE);
        Integer totalPrice = totalPrinter.getTotalPrice();
        outputView.printDetail(PrintMessage.TOTAL_PRICE, totalPrice);
        Integer freePrice = freePrinter.getTotalFree();
        outputView.printDetail(PrintMessage.PROMOTION_DISCOUNT_PRICE, freePrice);
        //멤버십 할인 출력
        outputView.printDetail(PrintMessage.PAY_PRICE, totalPrice - freePrice);
    }
}
