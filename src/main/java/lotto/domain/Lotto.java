package lotto.domain;

import java.util.HashSet;
import java.util.List;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();

        validate(lottoNumbers);
        this.numbers = lottoNumbers;
    }

    public List<Integer> getSortedNumbers() {
        return numbers.stream()
                .map(LottoNumber::value)
                .sorted()
                .toList();
    }

    public boolean has(int number) {
        LottoNumber target = new LottoNumber(number);
        return numbers.contains(target);
    }

    public int countMatches(Lotto winningLotto) {
        return (int) numbers.stream()
                .map(LottoNumber::value)
                .filter(winningLotto::has)
                .count();
    }

    private void validate(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateNotDuplicate(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또 번호는 %d개여야 합니다.", LOTTO_SIZE));
        }
    }

    private void validateNotDuplicate(List<LottoNumber> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }
}
