package store.service.generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ItemGeneratorTest {

    @DisplayName("대괄호로 열리지 않는 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"강아지-3]", "강[아지-4]", "강아;지-5", "강[아[지-7]"})
    void 대괄호로_열리지_않는_경우_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> new ItemGenerator(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("대괄호로 닫히지 않는 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"[강아지-1", "[강]아지-2", "[강아지-]3", "[강아]지-4"})
    void 대괄호로_닫히지_않는_경우_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> new ItemGenerator(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("대괄호가 없을 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"강아지-3", "강아지;-2", "강아;지-32"})
    void 대괄호가_없을_경우_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> new ItemGenerator(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("대괄호로 시작하고 끝나는 경우 정상동작한다.")
    @Test
    void 대괄호로_시작하고_끝나는_경우_정상동작한다() {
        String test = "[강아지-55]";
        String trueResult = "강아지-55";

        ItemGenerator itemGenerator = new ItemGenerator(test);

        assertThat(itemGenerator.getItem()).isEqualTo(trueResult);
    }

    @DisplayName("대괄호가 여러개 있는 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"[[강아지-1]]", "[[강아지-2]", "[강아]지-3]", "[강[아지-4]", "[강[아[지]-5]"})
    void 대괄호가_여러개_있는_경우_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> new ItemGenerator(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}