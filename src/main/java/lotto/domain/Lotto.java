package lotto.domain;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getSortedNumbers() {
        return numbers.stream().sorted().toList();
    }

    public boolean has(int number) {
        return numbers.contains(number);
    }

    public int countMatches(Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto::has)
                .count();
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateNotDuplicate(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LottoRule.SIZE) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또 번호는 %d개여야 합니다.", LottoRule.SIZE));
        }
    }

    private void validateRange(List<Integer> numbers) {
        boolean hasInvalidNumber = numbers.stream()
                .anyMatch(number -> number < LottoRule.MIN_NUMBER || number > LottoRule.MAX_NUMBER);

        if (hasInvalidNumber) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또 번호는 %d부터 %d 사이의 숫자여야 합니다.", LottoRule.MIN_NUMBER, LottoRule.MAX_NUMBER));
        }
    }

    private void validateNotDuplicate(List<Integer> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }

}
