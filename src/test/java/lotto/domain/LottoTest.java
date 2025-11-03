package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    @DisplayName("번호가 6개이며 모두 유효한 숫자인 경우 정상적으로 생성된다")
    void createWhenNumbersAreValid() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.getSortedNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 7})
    @DisplayName("번호가 6개가 아닌 경우 예외가 발생한다")
    void throwWhenNumbersNotSix(int size) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7).subList(0, size);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("번호가 1~45 범위를 벗어나면 예외가 발생한다")
    void throwWhenNumberOutOfRange(int invalidNumber) {
        assertThatThrownBy(() -> new Lotto(List.of(invalidNumber, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다")
    void throwWhenNumbersDuplicated() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 중복될 수 없습니다.");
    }

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
    @DisplayName("두 로또 간 일치 개수를 정확히 계산한다")
    void countMatchesReturnsCorrectCount() {
        Lotto base = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto other = new Lotto(List.of(1, 3, 5, 7, 9, 11));

        assertThat(base.countMatches(other)).isEqualTo(3);
    }
}