package lotto.domain;

public class Money {

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
            throw new IllegalArgumentException("[ERROR] 금액은 양수여야 합니다.");
        }
    }

    private void validateDivisibleByLottoPrice(int value) {
        if (value % LottoConstants.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(String.format("[ERROR] 구입 금액은 %,d원 단위여야 합니다.", LottoConstants.LOTTO_PRICE));
        }
    }
}
