package lotto.domain;

public record LottoNumber(int value) {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    public LottoNumber {
        if (value < MIN_NUMBER || value > MAX_NUMBER) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또 번호는 %d부터 %d 사이의 숫자여야 합니다.", MIN_NUMBER, MAX_NUMBER));
        }
    }
}
