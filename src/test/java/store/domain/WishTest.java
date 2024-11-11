package store.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WishTest {

    @DisplayName("수량이 음수일 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, -999999, -1000})
    void 수량이_음수일_경우_예외가_발생한다(int input) {
        assertThatThrownBy(() -> new Wish("name", input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("수량이 양수일 경우 정상작동한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 33, 444, 5567, 1000})
    void 수량이_양수일_경우_정상작동한다(int input) {
        Wish test = new Wish("name", input);

        assertThat(test.name()).isEqualTo("name");
        assertThat(test.amount()).isEqualTo(input);
    }
}