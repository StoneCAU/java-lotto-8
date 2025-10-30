package lotto.domain;

public class Money {

    private static final int LOTTO_PRICE = 1000;
    private final int amount;

    public Money(String input) {
        int value = parseToInt(input);
        validatePositive(value);
        validateDivisibleByLottoPrice(value);
        this.amount = value;
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
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

    private void validateDivisibleByLottoPrice(int value) {
        if (value % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 " + LOTTO_PRICE + "원 단위여야 합니다.");
        }
    }

}
