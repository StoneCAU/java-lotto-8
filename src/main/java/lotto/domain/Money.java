package lotto.domain;

public class Money {

    private static final int LOTTO_PRICE = 1000;
    private final int amount;

    public Money(int purchaseAmount) {
        validatePositive(purchaseAmount);
        validateDivisibleByLottoPrice(purchaseAmount);
        this.amount = purchaseAmount;
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
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
