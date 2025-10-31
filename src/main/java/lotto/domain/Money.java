package lotto.domain;

public class Money {

    private static final int LOTTO_PRICE = 1_000;
    private final int amount;

    public Money(int amount) {
        validatePositive(amount);
        validateDivisibleByLottoPrice(amount);
        this.amount = amount;
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
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
        if (value % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 " + LOTTO_PRICE + "원 단위여야 합니다.");
        }
    }

}
