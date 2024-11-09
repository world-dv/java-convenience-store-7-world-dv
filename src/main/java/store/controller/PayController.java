package store.controller;

import java.util.HashMap;
import java.util.List;
import store.domain.Pay;
import store.domain.Product;
import store.domain.Promotion;
import store.domain.Result;
import store.domain.Wish;
import store.service.DateCalculator;
import store.service.OriginalCalculator;
import store.service.PromotionCalculator;
import store.view.InputView;

public class PayController {

    private final InputView inputView;
    private final HashMap<String, List<Product>> products;
    private final HashMap<String, Promotion> promotions;

    public PayController(InputView inputView, HashMap<String, List<Product>> products,
                         HashMap<String, Promotion> promotions) {
        this.inputView = inputView;
        this.products = products;
        this.promotions = promotions;
    }

    public List<Result> run(List<Wish> wishes) {
        return wishes.stream().map(wish -> buy(products.get(wish.getName()), wish)).toList();
    }

    private Result buy(List<Product> products, Wish wish) {
        if (products.size() == 1) {
            return buyOneWay(products, wish);
        }
        return buyTwoWay(products, wish);
    }

    private Result buyOneWay(List<Product> products, Wish wish) {
        Product product = products.getFirst();
        if (product.getPromotion().equals("null")) {
            return new Result(wish.getName(), calculateOriginal(product, wish.getAmount()), null);
        }
        Promotion promotion = promotions.get(product.getPromotion());
        Pay promotionResult = calculatePromotion(product, promotion, wish.getAmount());

        DateCalculator dateCalculator = new DateCalculator(promotion, promotionResult);
        dateCalculator.calculate();

        if (promotionResult.getExtra().equals(-1)) {
            String userAnswer = inputView.readFree(wish.getName(), promotion.getContent().getLast());
            if (userAnswer.equals("Y")) {
                product.setQuantity(product.getQuantity() - promotion.getContent().getLast());
                promotionResult.addFreeAmount(promotion.getContent().getLast());
            }
        }
        return new Result(wish.getName(), null, promotionResult);
    }

    private Result buyTwoWay(List<Product> products, Wish wish) {
        Product promotionProduct = products.stream().filter(value -> !value.getPromotion().equals("null")).toList()
                .getFirst();
        Promotion promotion = promotions.get(promotionProduct.getPromotion());
        Pay promotionResult = calculatePromotion(promotionProduct, promotion, wish.getAmount());

        DateCalculator dateCalculator = new DateCalculator(promotion, promotionResult);
        dateCalculator.calculate();

        if (promotionResult.getExtra().equals(-1)) {
            String userAnswer = inputView.readFree(wish.getName(), promotion.getContent().getLast());
            if (userAnswer.equals("Y")) {
                promotionProduct.setQuantity(promotionProduct.getQuantity() - promotion.getContent().getLast());
                promotionResult.addFreeAmount(promotion.getContent().getLast());
            }
        }

        if (promotionResult.getExtra() > 0) {
            String userAnswer = inputView.readExtra(wish.getName(), promotionResult.getExtra());
            if (userAnswer.equals("Y")) {
                Product originalProduct = products.stream().filter(value -> value.getPromotion().equals("null"))
                        .toList()
                        .getFirst();
                Pay result = calculateOriginal(originalProduct, promotionResult.getExtra());
                return new Result(wish.getName(), result, promotionResult);
            }
        }
        return new Result(wish.getName(), null, promotionResult);
    }

    private Pay calculateOriginal(Product product, Integer amount) {
        OriginalCalculator originalCalculator = new OriginalCalculator(product, amount);
        return originalCalculator.calculate();
    }

    private Pay calculatePromotion(Product product, Promotion promotion, Integer amount) {
        PromotionCalculator promotionCalculator = new PromotionCalculator(product, promotion, amount);
        return promotionCalculator.calculate();
    }
}
