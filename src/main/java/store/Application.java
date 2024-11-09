package store;

import java.util.HashMap;
import java.util.List;
import store.controller.FileController;
import store.controller.ItemController;
import store.controller.PaymentController;
import store.controller.ReceiptController;
import store.controller.WishController;
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

        outputView.printlnMessage(PrintMessage.START_MESSAGE);

        outputView.printProduct(products);
        outputView.printlnMessage(PrintMessage.LINE_SPACE);

        ItemController itemController = new ItemController(inputView);
        WishController wishController = new WishController(products, itemController.run());
        PaymentController payController = new PaymentController(inputView, products, promotions);
        List<Result> result = payController.run(wishController.run());
        ReceiptController recipeController = new ReceiptController(inputView, outputView, result);
        recipeController.print();
    }
}
