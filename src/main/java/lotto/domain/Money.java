package lotto.domain;

public class Money {

    private final int amount;

    public Money(String input) {
        int value = parseToInt(input);
        validatePositive(value);
        this.amount = value;
    }

    private int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 금액은 숫자여야 합니다.");
        }
    }

    private void validatePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 양수여야 합니다.");
        }
    }

}
