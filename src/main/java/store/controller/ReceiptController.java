package store.controller;

import java.util.List;
import store.domain.Result;
import store.service.printer.FreePrinter;
import store.service.printer.MembershipPrinter;
import store.service.printer.OriginalPrinter;
import store.service.printer.TotalPrinter;
import store.view.InputView;
import store.view.OutputView;
import store.view.PrintMessage;

public class ReceiptController {

    private final InputView inputView;
    private final OutputView outputView;
    private final List<Result> results;

    public ReceiptController(InputView inputView, OutputView outputView, List<Result> results) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.results = results;
    }

    public void print() {
        TotalPrinter totalPrinter = new TotalPrinter(results);
        FreePrinter freePrinter = new FreePrinter(results);
        MembershipPrinter membershipPrinter = new MembershipPrinter();
        OriginalPrinter originalPrinter = new OriginalPrinter(results);
        int membership = membershipPrinter.calculate(inputView.readMembership(), originalPrinter.calculateOriginal());
        printHead(totalPrinter, freePrinter);
        printBody(totalPrinter);
        printTail(totalPrinter.calculateTotalPrice(), freePrinter.calculateTotalFree(), membership);
    }

    private void printHead(TotalPrinter totalPrinter, FreePrinter freePrinter) {
        outputView.printlnMessage(PrintMessage.LINE_CONVENIENCE_STORE);
        outputView.printlnMessage(PrintMessage.TABLE_SETTING);
        outputView.printTotal(totalPrinter.calculate());
        outputView.printlnMessage(PrintMessage.LINE_FREE);
        outputView.printFree(freePrinter.calculate());
    }

    private void printBody(TotalPrinter totalPrinter) {
        outputView.printlnMessage(PrintMessage.LINE);
        outputView.printTotalDetail(totalPrinter);
    }

    private void printTail(int totalPrice, int freePrice, int membership) {
        outputView.printPromotion(freePrice);
        outputView.printMembership(membership);

        outputView.printPayMoney(totalPrice - freePrice - membership);
        outputView.printlnMessage(PrintMessage.LINE_SPACE);
    }
}
