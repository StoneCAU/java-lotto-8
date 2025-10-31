package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    @Nested
    @DisplayName("로또를 생성할 때")
    class CreateTest {

        @Test
        @DisplayName("번호가 6개이며 모두 유효한 숫자인 경우 정상적으로 생성된다")
        void createWhenNumbersAreValid() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            assertThat(lotto.getSortedNumbers())
                    .containsExactly(1, 2, 3, 4, 5, 6);
        }

        @Test
        @DisplayName("번호가 6개 미만인 경우 예외가 발생한다")
        void throwWhenNumbersLessThanSix() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 로또 번호는 6개여야 합니다.");
        }

        @Test
        @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다")
        void throwWhenNumbersExceedSix() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 로또 번호는 6개여야 합니다.");
        }

        @Test
        @DisplayName("번호가 1보다 작은 숫자가 포함된 경우 예외가 발생한다")
        void throwWhenNumberIsLessThanOne() {
            assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }

        @Test
        @DisplayName("번호가 45보다 큰 숫자가 포함된 경우 예외가 발생한다")
        void throwWhenNumberIsGreaterThanFortyFive() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }

        @Test
        @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
        void throwWhenNumbersDuplicated() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("로또 기능을 검증할 때")
    class ValidationTest {

        @Test
        @DisplayName("주어진 번호가 포함된 경우 has()는 true를 반환한다")
        void hasReturnsTrueWhenContainsNumber() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            assertThat(lotto.has(3)).isTrue();
        }

        @Test
        @DisplayName("주어진 번호가 포함되지 않은 경우 has()는 false를 반환한다")
        void hasReturnsFalseWhenNotContainsNumber() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            assertThat(lotto.has(7)).isFalse();
        }

        @Test
        @DisplayName("두 로또 간 일치 개수를 비교할 때 countMatches()가 정확히 계산된다")
        void countMatchesReturnsCorrectCount() {
            Lotto base = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            Lotto other = new Lotto(List.of(1, 3, 5, 7, 9, 11));
            assertThat(base.countMatches(other)).isEqualTo(3);
        }
    }
}
