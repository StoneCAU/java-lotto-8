package lotto.domain;

public class Money {

    private static final String ERROR_NOT_POSITIVE = "[ERROR] 금액은 양수여야 합니다.";
    private static final String ERROR_NOT_DIVISIBLE = "[ERROR] 구입 금액은 %,d원 단위여야 합니다.";

    private final int amount;

    public Money(int amount) {
        validatePositive(amount);
        validateDivisibleByLottoPrice(amount);
        this.amount = amount;
    }

    public int calculateLottoCount() {
        return amount / LottoConstants.LOTTO_PRICE;
    }

    public double calculateProfitRate(int totalPrize) {
        return (double) totalPrize / amount * 100;
    }

    private void validatePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(ERROR_NOT_POSITIVE);
        }
    }

    private void validateDivisibleByLottoPrice(int value) {
        if (value % LottoConstants.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(String.format(ERROR_NOT_DIVISIBLE, LottoConstants.LOTTO_PRICE));
        }
    }
}
