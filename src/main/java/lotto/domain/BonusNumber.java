package lotto.domain;

public class BonusNumber {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final int value;

    public BonusNumber(int value, Lotto winningLotto) {
        validateRange(value);
        validateNotDuplicate(value, winningLotto);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private void validateRange(int value) {
        if (value < MIN_NUMBER || value > MAX_NUMBER) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 보너스 번호는 %d부터 %d 사이의 숫자여야 합니다.", MIN_NUMBER, MAX_NUMBER)
            );
        }
    }

    private void validateNotDuplicate(int value, Lotto winningLotto) {
        if (winningLotto.has(value)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
