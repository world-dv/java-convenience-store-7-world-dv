package store.controller;

import java.util.List;
import store.domain.Result;
import store.service.printer.FreePrinter;
import store.service.printer.MembershipPrinter;
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
        MembershipPrinter membershipPrinter = new MembershipPrinter(results);
        Integer membership = membershipPrinter.getDiscount(inputView.readMembership());

        outputView.printlnMessage(PrintMessage.LINE_CONVENIENCE_STORE);
        outputView.printTotal(totalPrinter.calculate());

        outputView.printlnMessage(PrintMessage.LINE_FREE);

        outputView.printFree(freePrinter.calculate());
        outputView.printlnMessage(PrintMessage.LINE);

        Integer totalPrice = totalPrinter.getTotalPrice();
        outputView.printDetail(PrintMessage.TOTAL_PRICE, totalPrice);

        Integer freePrice = freePrinter.getTotalFree();
        outputView.printDetail(PrintMessage.PROMOTION_DISCOUNT_PRICE, freePrice);

        outputView.printDetail(PrintMessage.MEMBERSHIP_DISCOUNT_PRICE, membership);

        outputView.printDetail(PrintMessage.PAY_PRICE, totalPrice - freePrice - membership);
        outputView.printlnMessage(PrintMessage.LINE_SPACE);
    }
}
