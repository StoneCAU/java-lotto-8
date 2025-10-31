package lotto.validator;

import lotto.domain.Lotto;
import lotto.domain.LottoRule;

public class BonusNumberValidator {

    private BonusNumberValidator() {
    }

    public static void validate(Lotto winningLotto, int bonusNumber) {
        validateRange(bonusNumber);
        validateNotDuplicate(winningLotto, bonusNumber);
    }

    private static void validateRange(int bonusNumber) {
        if (bonusNumber < LottoRule.MIN_NUMBER || bonusNumber > LottoRule.MAX_NUMBER) {
            throw new IllegalArgumentException(String.format("[ERROR] 보너스 번호는 %d부터 %d 사이의 숫자여야 합니다.", LottoRule.MIN_NUMBER, LottoRule.MAX_NUMBER));
        }
    }

    private static void validateNotDuplicate(Lotto winningLotto, int bonusNumber) {
        if (winningLotto.has(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
