package store.controller;

import java.util.HashMap;
import java.util.List;
import store.domain.Product;
import store.domain.Promotion;
import store.domain.Wish;
import store.service.OriginalCalculator;
import store.service.PromotionCalculator;

public class PayController {

    private final HashMap<String, List<Product>> products;
    private final HashMap<String, Promotion> promotions;

    public PayController(HashMap<String, List<Product>> products, HashMap<String, Promotion> promotions) {
        this.products = products;
        this.promotions = promotions;
    }

    public void run(List<Wish> wishes) {
        wishes.forEach(x -> buy(products.get(x.getName()), x));
    }

    public void buy(List<Product> products, Wish wish) {
        if (products.size() == 1) {
            Product product = products.getFirst();
            if (product.getPromotion().equals("null")) {
                OriginalCalculator originalCalculator = new OriginalCalculator(product, wish.getAmount());
                System.out.println(originalCalculator.calculate());
                return;
            }
            Promotion promotion = promotions.get(product.getPromotion());
            PromotionCalculator promotionCalculator = new PromotionCalculator(product, promotion, wish.getAmount());
            List<Integer> promotionResult = promotionCalculator.calculate();
            System.out.println(promotionResult.toString());

            if (promotionResult.getLast() == -1) {
                //추가 공짜 받을 건지 입력받고 입력 받으면 get 더함
            }
            return;
        }
        int amount = wish.getAmount();
        Product promotionProduct = products.stream().filter(value -> !value.getPromotion().equals("null")).toList()
                .getFirst();
        PromotionCalculator promotionCalculator = new PromotionCalculator(promotionProduct,
                promotions.get(promotionProduct.getPromotion()), wish.getAmount());
        List<Integer> promotionResult = promotionCalculator.calculate();
        System.out.println(promotionResult.toString());

        if (promotionResult.getLast() > 0) {
            //추가 결제 여부 입력받고 입력 받으면 추가 결제 실행
            Product originalProduct = products.stream().filter(value -> value.getPromotion().equals("null")).toList()
                    .getFirst();
        }
    }
}
