package store;

import java.util.HashMap;
import java.util.List;
import store.controller.FileController;
import store.controller.WishController;
import store.controller.PaymentController;
import store.controller.ReceiptController;
import store.domain.Product;
import store.domain.Promotion;
import store.domain.Result;
import store.view.InputView;
import store.view.OutputView;
import store.view.PrintMessage;

public class Application {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        FileController fileController = new FileController();
        HashMap<String, List<Product>> products = fileController.createProduct();
        HashMap<String, Promotion> promotions = fileController.createPromotion();

        do {
            run(inputView, outputView, products, promotions);
        } while (checkRestart(inputView.readRestart()));
    }

    public static void run(InputView inputView, OutputView outputView, HashMap<String, List<Product>> products, HashMap<String, Promotion> promotions) {
        outputView.printlnMessage(PrintMessage.START_MESSAGE);
        outputView.printProduct(products);
        WishController itemController = new WishController(inputView, outputView, products);
        PaymentController payController = new PaymentController(inputView, products, promotions);
        List<Result> result = payController.run(itemController.run());
        ReceiptController recipeController = new ReceiptController(inputView, outputView, result);
        recipeController.print();
    }

    public static boolean checkRestart(String restart) {
        return restart.equals("Y");
    }
}
