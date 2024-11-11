package store.service.separator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WishSeparatorTest {

    @DisplayName("바가 하나 포함되어 있는 경우 상품명과 수량을 분리한다.")
    @ParameterizedTest
    @ValueSource(strings = {"강아지-1", "고양이-2", "고구마-3", "감자-67"})
    void 바가_하나_포함되어_있는_경우_상품명과_수량을_분리한다(String input) {
        WishSeparator test = new WishSeparator(input);

        assertThat(test.getSplitItem()).isEqualTo(List.of(input.split("-")));
    }

    @DisplayName("바가 여러개 포함되어 있는 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"강아지--1", "고양이-1-", "-고구마-2-", "감자---3"})
    void 바가_여러개_포함되어_있는_경우_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> new WishSeparator(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("바가 포함되어 있지 않은 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"강아지1", "고양이,1", "고구마=2", "감자;3"})
    void 바가_포함되어_있지_않은_경우_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> new WishSeparator(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}