package lotto.domain;

import java.util.List;

public class FixedLottoNumberGenerator implements LottoNumberGenerator {

    private final List<Integer> numbers;

    public FixedLottoNumberGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<Integer> generate() {
        return numbers;
    }
}
