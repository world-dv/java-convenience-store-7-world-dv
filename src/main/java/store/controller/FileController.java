package store.controller;

import java.util.HashMap;
import java.util.List;
import store.domain.Product;
import store.domain.Promotion;
import store.service.generator.ProductGenerator;
import store.service.generator.PromotionGenerator;

public class FileController {

    public HashMap<String, List<Product>> createProduct() {
        ProductGenerator productGenerator = new ProductGenerator();
        return productGenerator.generate();
    }

    public HashMap<String, Promotion> createPromotion() {
        PromotionGenerator promotionGenerator = new PromotionGenerator();
        return promotionGenerator.generate();
    }
}
