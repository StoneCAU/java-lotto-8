package lotto.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputParserTest {

    @Test
    @DisplayName("쉼표로 구분된 숫자 문자열을 올바르게 파싱한다")
    void parseCommaSeparatedNumbers() {
        List<Integer> numbers = InputParser.parseNumbers("1,2,3,4,5,6");

        assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("공백이 포함된 경우 정상적으로 파싱된다")
    void parseWithSpaces() {
        List<Integer> numbers = InputParser.parseNumbers(" 1 , 2 , 3 ");

        assertThat(numbers).containsExactly(1, 2, 3);
    }

    @Test
    @DisplayName("하나의 숫자만 입력된 경우 리스트로 반환된다")
    void parseSingleNumber() {
        List<Integer> numbers = InputParser.parseNumbers("5");

        assertThat(numbers).containsExactly(5);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("빈 문자열 또는 null 입력 시 예외가 발생한다")
    void throwWhenNullOrEmpty(String input) {
        assertThatThrownBy(() -> InputParser.parseNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력값을 입력해주세요.");
    }

    @Test
    @DisplayName("숫자가 아닌 값이 포함된 경우 예외가 발생한다")
    void throwWhenContainsNonNumericValue() {
        assertThatThrownBy(() -> InputParser.parseNumbers("1,a,3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 숫자만 입력 가능합니다.");
    }

    @Test
    @DisplayName("빈 번호가 포함된 경우 예외가 발생한다")
    void throwWhenContainsEmptyToken() {
        assertThatThrownBy(() -> InputParser.parseNumbers("1,,2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 빈 번호는 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("정상적인 숫자 문자열을 정수로 변환한다")
    void parseValidInteger() {
        int result = InputParser.parseSingleNumber("1234");

        assertThat(result).isEqualTo(1234);
    }

    @Test
    @DisplayName("공백이 포함된 단일 숫자를 정상적으로 파싱한다")
    void parseSingleNumberWithSpaces() {
        int result = InputParser.parseSingleNumber("  42 ");

        assertThat(result).isEqualTo(42);
    }

    @Test
    @DisplayName("단일 숫자 파싱 시 숫자가 아닌 값 입력 시 예외가 발생한다")
    void throwWhenSingleNumberNonNumeric() {
        assertThatThrownBy(() -> InputParser.parseSingleNumber("a123"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 숫자를 입력해야 합니다.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("단일 숫자 파싱 시 빈 문자열 또는 null 입력 시 예외가 발생한다")
    void throwWhenSingleNumberNullOrEmpty(String input) {
        assertThatThrownBy(() -> InputParser.parseSingleNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력값을 입력해주세요.");
    }
}