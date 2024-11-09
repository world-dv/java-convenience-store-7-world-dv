package store;

import java.util.HashMap;
import java.util.List;
import store.controller.ItemController;
import store.controller.PaymentController;
import store.controller.RecipeController;
import store.controller.WishController;
import store.domain.Product;
import store.domain.Promotion;
import store.domain.Result;
import store.domain.Wish;
import store.service.generator.ItemGenerator;
import store.service.generator.ProductGenerator;
import store.service.generator.PromotionGenerator;
import store.view.InputView;
import store.view.OutputView;
import store.view.PrintMessage;

public class Application {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();

        outputView.printlnMessage(PrintMessage.START_MESSAGE);

        ProductGenerator productGenerator = new ProductGenerator();
        HashMap<String, List<Product>> products = productGenerator.generate();

        PromotionGenerator promotionGenerator = new PromotionGenerator();
        HashMap<String, Promotion> promotions = promotionGenerator.generate();

        outputView.printProduct(products);
        outputView.printlnMessage(PrintMessage.LINE_SPACE);

        ItemController itemController = new ItemController(inputView);
        List<ItemGenerator> itemGenerators = itemController.run();

        WishController wishController = new WishController(products, itemGenerators);
        List<Wish> wishes = wishController.run();

        PaymentController payController = new PaymentController(inputView, products, promotions);
        List<Result> result = payController.run(wishes);

        RecipeController recipeController = new RecipeController(outputView, result);
        recipeController.print();
    }
}
