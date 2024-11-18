package store.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SeparatorTest {

    @DisplayName("쉼표로 상품들을 분리한다.")
    @ParameterizedTest
    @ValueSource(strings = {"[고구마-3],[맛탕-2],[감자-1]", "[고등어-2],[참치-2]", "[강아지-1],[고양이-100]"})
    void 쉼표로_상품들을_분리한다(String input) {
        List<String> test = Separator.splitItems(input);
        List<String> trueResult = List.of(input.split(",", -1));

        assertThat(test.getFirst()).isEqualTo(trueResult.getFirst());
        assertThat(test.getLast()).isEqualTo(trueResult.getLast());
    }

    @DisplayName("바로 상품명과 수량들을 분리한다.")
    @ParameterizedTest
    @ValueSource(strings = {"고구마-3", "맛탕-2", "감자-1", "고등어-2", "참치-2", "강아지-1", "고양이-100"})
    void 바로_상품명과_수량들을_분리한다(String input) {
        List<String> test = Separator.splitItem(input);
        List<String> trueResult = List.of(input.split("-", 2));

        assertThat(test.getFirst()).isEqualTo(trueResult.getFirst());
        assertThat(test.getLast()).isEqualTo(trueResult.getLast());
    }
}