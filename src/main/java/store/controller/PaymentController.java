package store.controller;

import java.util.HashMap;
import java.util.List;
import store.domain.Payment;
import store.domain.Product;
import store.domain.Promotion;
import store.domain.Result;
import store.domain.Wish;
import store.service.calculator.DateCalculator;
import store.service.calculator.OriginalCalculator;
import store.service.calculator.PromotionCalculator;
import store.view.InputView;

public class PaymentController {

    private static final String NOT_BUY = "null";
    private static final Payment NOT_THING = null;
    private static final Integer ONLY_ONE_PRODUCT = 1;
    private static final String TRUE = "Y";

    private final InputView inputView;
    private final HashMap<String, List<Product>> products;
    private final HashMap<String, Promotion> promotions;

    public PaymentController(InputView inputView, HashMap<String, List<Product>> products,
                             HashMap<String, Promotion> promotions) {
        this.inputView = inputView;
        this.products = products;
        this.promotions = promotions;
    }

    public List<Result> run(List<Wish> wishes) {
        return wishes.stream().map(wish -> buy(products.get(wish.name()), wish)).toList();
    }

    private Result buy(List<Product> buyProducts, Wish wish) {
        if (isProductOnlyOne(buyProducts)) {
            return buyOneWay(buyProducts, wish);
        }
        return buyTwoWay(buyProducts, wish);
    }

    private Result buyOneWay(List<Product> buyProducts, Wish wish) {
        Product product = buyProducts.getFirst();
        if (product.haveOriginOnly()) {
            return new Result(wish.name(), calculateOriginal(product, wish.amount()), NOT_THING);
        }
        Promotion promotion = promotions.get(product.getPromotion());
        Payment promotionResult = calculatePromotion(product, promotion, wish.amount());

        runDateCalculate(promotion, promotionResult);

        if (promotionResult.haveExtraFree()) {
            String userAnswer = inputExtraFreeAnswer(wish, promotion);
            if (sayUserYes(userAnswer)) {
                promotionResult.addFreeAmount(promotion.getGet());
                product.setQuantity(product.getQuantity() - promotion.getGet());
            }
        }

        if (promotionResult.haveExtra()) {
            String userAnswer = inputExtraAnswer(wish, promotionResult);
            if (sayUserYes(userAnswer)) {
                promotionResult.changeMembership();
                promotionResult.setBuyAmount(promotionResult.getBuyAmount() + promotionResult.getExtraAmount());
                product.setQuantity(product.getQuantity() - promotionResult.getExtraAmount());
            }
        }
        return new Result(wish.name(), NOT_THING, promotionResult);
    }

    private Result buyTwoWay(List<Product> buyProducts, Wish wish) {
        Product product = findPromotion(buyProducts);
        Promotion promotion = promotions.get(product.getPromotion());
        Payment promotionResult = calculatePromotion(product, promotion, wish.amount());

        runDateCalculate(promotion, promotionResult);

        if (promotionResult.haveExtraFree()) {
            String userAnswer = inputExtraFreeAnswer(wish, promotion);
            if (sayUserYes(userAnswer)) {
                product.setQuantity(product.getQuantity() - promotion.getGet());
                promotionResult.addFreeAmount(promotion.getGet());
            }
            return new Result(wish.name(), NOT_THING, promotionResult);
        }

        if (promotionResult.haveExtra()) {
            String userAnswer = inputExtraAnswer(wish, promotionResult);
            if (sayUserYes(userAnswer)) {
                promotionResult.changeMembership();
                product.setQuantity(product.getQuantity() - promotionResult.getExtraAmount());
                promotionResult.setBuyAmount(promotionResult.getBuyAmount() + promotionResult.getExtraAmount());

                Product originalProduct = findOriginal(buyProducts);
                Payment originalResult = calculateOriginal(originalProduct, promotionResult.getMorePayAmount());
                return new Result(wish.name(), originalResult, promotionResult);
            }
        }
        return new Result(wish.name(), NOT_THING, promotionResult);
    }

    private boolean isProductOnlyOne(List<Product> buyProducts) {
        return buyProducts.size() == ONLY_ONE_PRODUCT;
    }

    private Product findPromotion(List<Product> buyProducts) {
        return buyProducts.stream().filter(value -> !value.getPromotion().equals(NOT_BUY)).toList().getFirst();
    }

    private Product findOriginal(List<Product> buyProducts) {
        return buyProducts.stream().filter(value -> value.getPromotion().equals(NOT_BUY)).toList().getFirst();
    }

    private String inputExtraFreeAnswer(Wish wish, Promotion promotion) {
        return inputView.readFree(wish.name(), promotion.getGet());
    }

    private String inputExtraAnswer(Wish wish, Payment payment) {
        return inputView.readExtra(wish.name(), payment.getExtraAmount() + payment.getMorePayAmount());
    }

    private boolean sayUserYes(String user) {
        return user.equals(TRUE);
    }

    private Payment calculateOriginal(Product product, Integer amount) {
        OriginalCalculator originalCalculator = new OriginalCalculator(product, amount);
        return originalCalculator.calculate();
    }

    private Payment calculatePromotion(Product product, Promotion promotion, Integer amount) {
        PromotionCalculator promotionCalculator = new PromotionCalculator(product, promotion, amount);
        return promotionCalculator.calculate();
    }

    private void runDateCalculate(Promotion promotion, Payment promotionResult) {
        DateCalculator dateCalculator = new DateCalculator(promotion, promotionResult);
        dateCalculator.calculate();
    }
}
