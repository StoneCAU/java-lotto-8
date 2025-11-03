package lotto.domain;

public record LottoNumber(int value) {

    private static final String ERROR_OUT_OF_RANGE = "[ERROR] 로또 번호는 %d부터 %d 사이의 숫자여야 합니다.";

    public LottoNumber {
        if (value < LottoConstants.MIN_NUMBER || value > LottoConstants.MAX_NUMBER) {
            throw new IllegalArgumentException(
                    String.format(ERROR_OUT_OF_RANGE, LottoConstants.MIN_NUMBER, LottoConstants.MAX_NUMBER)
            );
        }
    }
}
