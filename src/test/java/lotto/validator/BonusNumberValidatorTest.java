package lotto.validator;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("BonusNumberValidator 테스트")
public class BonusNumberValidatorTest {

    private static final Lotto WINNING_LOTTO = new Lotto(List.of(1, 2, 3, 4, 5, 6));

    @Nested
    @DisplayName("보너스 번호 검증 시")
    class ValidateTest {

        @Test
        @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
        void throwWhenBonusNumberIsDuplicated() {
            int bonusNumber = 3;

            assertThatThrownBy(() -> BonusNumberValidator.validate(WINNING_LOTTO, bonusNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }

        @Test
        @DisplayName("보너스 번호가 1 미만이면 예외가 발생한다")
        void throwWhenBonusNumberBelowRange() {
            int bonusNumber = 0;

            assertThatThrownBy(() -> BonusNumberValidator.validate(WINNING_LOTTO, bonusNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }

        @Test
        @DisplayName("보너스 번호가 45를 초과하면 예외가 발생한다")
        void throwWhenBonusNumberAboveRange() {
            int bonusNumber = 46;

            assertThatThrownBy(() -> BonusNumberValidator.validate(WINNING_LOTTO, bonusNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }

        @Test
        @DisplayName("보너스 번호가 유효하고 당첨 번호와 중복되지 않으면 통과한다")
        void passesWhenBonusNumberIsValid() {
            int bonusNumber = 7;

            assertThatCode(() -> BonusNumberValidator.validate(WINNING_LOTTO, bonusNumber))
                    .doesNotThrowAnyException();
        }
    }
}
