package store.service.generator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.Product;

class ProductGeneratorTest {

    private HashMap<String, List<Product>> products;

    @BeforeEach
    void before_each() {
        products = new ProductGenerator().generate();
    }

    @DisplayName("주어진 경로 내의 상품 파일을 읽어 저장한다.")
    @Test
    void 주어진_경로_내의_상품_파일을_읽어_저장한다() {
        String trueResultName = "오렌지주스";
        Product trueResultPromotion = new Product(1800, 9, "MD추천상품");
        Product trueResultProduct = new Product(1800, 0, "null");

        List<Product> result = products.get(trueResultName);
        assertThat(result.getFirst().getPrice()).isEqualTo(trueResultPromotion.getPrice());
        assertThat(result.getFirst().getQuantity()).isEqualTo(trueResultPromotion.getQuantity());
        assertThat(result.getFirst().getPromotion()).isEqualTo(trueResultPromotion.getPromotion());

        assertThat(result.getLast().getPrice()).isEqualTo(trueResultProduct.getPrice());
        assertThat(result.getLast().getQuantity()).isEqualTo(trueResultProduct.getQuantity());
        assertThat(result.getLast().getPromotion()).isEqualTo(trueResultProduct.getPromotion());
    }
}