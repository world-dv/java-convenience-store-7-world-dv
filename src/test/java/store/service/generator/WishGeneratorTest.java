package store.service.generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.domain.Product;
import store.domain.Wish;

class WishGeneratorTest {

    private HashMap<String, List<Product>> products;

    @BeforeEach
    void before_each() {
        products = new ProductGenerator().generate();
    }

    @DisplayName("입력한 상품명이 제공되는 상품명과 일치하지 않을 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"고구마", "당나귀", "말미잘", "고양이", "치킨", "오렌지스무디"})
    void 입력한_상품명이_제공되는_상품명과_일치하지_않을_경우_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> new WishGenerator(products, List.of(input, "10")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("수량이 정수가 아닐 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"수량", "1-2", "1--", "+2+", "3;s", "!v;f"})
    void 수량이_정수가_아닐_경우_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> new WishGenerator(products, List.of("오렌지주스", input)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("수량이 주어진 수량의 범위를 벗어나는 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"100", "1000", "10000", "100000", "1000000", "10000000"})
    void 수량이_주어진_수량의_범위를_벗어나는_경우_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> new WishGenerator(products, List.of("오렌지주스", input)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("입력이 상품명과 일치하고 수량이 범위 내 정수일 경우 정상동작합니다.")
    @Test
    void 입력이_상품명과_일치하고_수량이_범위_내_정수일_경우_정상동작합니다() {
        String testName = "오렌지주스";
        String testAmount = "5";

        Wish result = new WishGenerator(products, List.of(testName, testAmount)).createWish();

        assertThat(result.name()).isEqualTo(testName);
        assertThat(result.amount()).isEqualTo(Integer.parseInt(testAmount));
    }
}