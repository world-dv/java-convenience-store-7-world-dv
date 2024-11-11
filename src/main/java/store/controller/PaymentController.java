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
    private static final Integer NOT_FOUND = 0;
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
        return buyTwoWay(buyProducts, wish);
    }

    private Result buyTwoWay(List<Product> buyProducts, Wish wish) {
        Product originalProduct = findOriginal(buyProducts);
        Product promotionProduct = findPromotion(buyProducts);
        if (promotionProduct == null || promotionProduct.getQuantity().equals(NOT_FOUND)) {
            return new Result(wish.name(), calculateOriginal(originalProduct, wish.amount()), NOT_THING);
        }
        Promotion promotion = promotions.get(promotionProduct.getPromotion());
        if (runDateCalculate(promotion) && originalProduct.getQuantity() > 0) {
            Payment originResult = calculateOriginal(originalProduct, wish.amount());
            return runPromotion(wish, promotionProduct, originResult);
        }
        Payment promotionResult = calculatePromotion(promotionProduct, promotion, wish.amount());
        return runOriginal(wish, promotion, promotionProduct, originalProduct, promotionResult);
    }

    private Result runOriginal(Wish wish, Promotion promotion, Product promotionProduct, Product originalProduct,
                                   Payment promotionResult) {
        if (promotionResult.haveExtraFree()) {
            return buyFree(wish, promotion, promotionProduct, promotionResult);
        }
        if (promotionResult.haveExtra()) {
            return buyExtra(wish, promotionProduct, originalProduct, promotionResult);
        }
        return new Result(wish.name(), NOT_THING, promotionResult);
    }

    private Result runPromotion(Wish wish, Product promotionProduct, Payment originalResult) {
        if (originalResult.haveExtra()) {
            return buyExtraOriginal(wish, promotionProduct, originalResult);
        }
        return new Result(wish.name(), originalResult, NOT_THING);
    }

    private Result buyFree(Wish wish, Promotion promotion, Product promotionProduct, Payment promotionResult) {
        String userAnswer = inputExtraFreeAnswer(wish, promotion);
        if (sayUserYes(userAnswer)) {
            promotionProduct.setQuantity(promotionProduct.getQuantity() - promotion.getGet());
            promotionResult.addFreeAmount(promotion.getGet());
        }
        return new Result(wish.name(), NOT_THING, promotionResult);
    }

    private Result buyExtraOriginal(Wish wish, Product promotionProduct, Payment payment) {
        String userAnswer = inputExtraAnswer(wish, payment);
        if (sayUserYes(userAnswer)) {
            promotionProduct.setQuantity(promotionProduct.getQuantity() - payment.getExtraAmount());
            payment.setBuyAmount(payment.getBuyAmount() + payment.getExtraAmount());
        }
        return new Result(wish.name(), payment, NOT_THING);
    }

    private Result buyExtra(Wish wish, Product promotionProduct, Product originalProduct, Payment payment) {
        String userAnswer = inputExtraAnswer(wish, payment);
        if (sayUserYes(userAnswer)) {
            return buyExtraTwoWay(wish, promotionProduct, originalProduct, payment);
        }
        return new Result(wish.name(), NOT_THING, payment);
    }

    private Result buyExtraTwoWay(Wish wish, Product promotionProduct, Product originalProduct, Payment payment) {
        payment.changeMembership();
        promotionProduct.setQuantity(promotionProduct.getQuantity() - payment.getExtraAmount());
        payment.setBuyAmount(payment.getBuyAmount() + payment.getExtraAmount());
        if (originalProduct != null) {
            Payment originalResult = calculateOriginal(originalProduct, payment.getMorePayAmount());
            return new Result(wish.name(), originalResult, payment);
        }
        return new Result(wish.name(), NOT_THING, payment);
    }

    private Product findPromotion(List<Product> buyProducts) {
        return buyProducts.stream().filter(value -> !value.getPromotion().equals(NOT_BUY)).findFirst().orElse(null);
    }

    private Product findOriginal(List<Product> buyProducts) {
        return buyProducts.stream().filter(value -> value.getPromotion().equals(NOT_BUY)).findFirst().orElse(null);
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
        return promotionCalculator.calculate(runDateCalculate(promotion));
    }

    private boolean runDateCalculate(Promotion promotion) {
        DateCalculator dateCalculator = new DateCalculator(promotion);
        return dateCalculator.calculate();
    }
}
