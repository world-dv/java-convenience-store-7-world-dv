package store.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ValidationTest {

    @DisplayName("입력이 Y또는 N일 경우 정상동작한다.")
    @ParameterizedTest
    @ValueSource(strings = {"Y", "N"})
    void 입력이_Y또는_N일_경우_정상동작한다(String input) {
        String test = Validation.validateInput(input);

        assertThat(test).isEqualTo(input);
    }

    @DisplayName("입력이 Y또는 N이 아닐 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"강아지", "고양이", "woo", "ha", "1w2", "YYYY", "N2", ";YN", "YN"})
    void 입력이_Y또는_N이_아닐_경우_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> Validation.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}