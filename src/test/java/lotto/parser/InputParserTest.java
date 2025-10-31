package lotto.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("InputParser 테스트")
public class InputParserTest {

    @Nested
    @DisplayName("문자열을 숫자 리스트로 파싱할 때")
    class ParseNumbersTest {

        @Test
        @DisplayName("쉼표로 구분된 숫자 문자열을 올바르게 파싱한다")
        void parseCommaSeparatedNumbers() {
            List<Integer> numbers = InputParser.parseNumbers("1,2,3,4,5,6");
            assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @Test
        @DisplayName("공백이 포함된 경우 정상적으로 파싱한다")
        void parseWithSpaces() {
            List<Integer> numbers = InputParser.parseNumbers(" 1 , 2 , 3 ");
            assertThat(numbers).containsExactly(1, 2, 3);
        }

        @Test
        @DisplayName("하나의 숫자만 입력된 경우 리스트로 반환한다")
        void parseSingleNumber() {
            List<Integer> numbers = InputParser.parseNumbers("5");
            assertThat(numbers).containsExactly(5);
        }

        @Test
        @DisplayName("빈 문자열 입력 시 예외가 발생한다")
        void throwWhenEmptyString() {
            assertThatThrownBy(() -> InputParser.parseNumbers(""))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 입력값을 입력해주세요.");
        }

        @Test
        @DisplayName("null 입력 시 예외가 발생한다")
        void throwWhenNullInput() {
            assertThatThrownBy(() -> InputParser.parseNumbers(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 입력값을 입력해주세요.");
        }

        @Test
        @DisplayName("숫자가 아닌 값이 포함된 경우 예외가 발생한다")
        void throwWhenContainsNonNumericValue() {
            assertThatThrownBy(() -> InputParser.parseNumbers("1,a,3"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 숫자를 입력하세요.");
        }
    }

    @Nested
    @DisplayName("단일 숫자 문자열을 파싱할 때")
    class ParseIntTest {

        @Test
        @DisplayName("정상적인 숫자 문자열을 정수로 변환한다")
        void parseValidInteger() {
            int result = InputParser.parseInt("1234");
            assertThat(result).isEqualTo(1234);
        }

        @Test
        @DisplayName("공백이 포함된 경우 정상적으로 파싱된다")
        void parseWithSpaces() {
            int result = InputParser.parseInt("  42 ");
            assertThat(result).isEqualTo(42);
        }

        @Test
        @DisplayName("숫자가 아닌 값 입력 시 예외가 발생한다")
        void throwWhenNonNumeric() {
            assertThatThrownBy(() -> InputParser.parseInt("a123"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 숫자를 입력하세요.");
        }

        @Test
        @DisplayName("빈 문자열 입력 시 예외가 발생한다")
        void throwWhenEmptyString() {
            assertThatThrownBy(() -> InputParser.parseInt(""))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 입력값을 입력해주세요.");
        }

        @Test
        @DisplayName("null 입력 시 예외가 발생한다")
        void throwWhenNullInput() {
            assertThatThrownBy(() -> InputParser.parseInt(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 입력값을 입력해주세요.");
        }
    }
}
