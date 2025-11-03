package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusNumberTest {

    private static final Lotto WINNING_LOTTO = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    private static final int VALID_BONUS_NUMBER = 7;

    @Test
    @DisplayName("당첨 번호와 중복되지 않는 유효한 보너스 번호를 생성한다")
    void createValidBonusNumber() {
        BonusNumber bonusNumber = new BonusNumber(VALID_BONUS_NUMBER, WINNING_LOTTO);

        assertThat(bonusNumber.getValue()).isEqualTo(VALID_BONUS_NUMBER);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46, 100})
    @DisplayName("1~45 범위를 벗어나면 예외가 발생한다")
    void throwWhenNumberOutOfRange(int invalidNumber) {
        assertThatThrownBy(() -> new BonusNumber(invalidNumber, WINNING_LOTTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 6})
    @DisplayName("당첨 번호와 중복되면 예외가 발생한다")
    void throwWhenDuplicateWithWinningNumbers(int duplicateNumber) {
        assertThatThrownBy(() -> new BonusNumber(duplicateNumber, WINNING_LOTTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }
}
