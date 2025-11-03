package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RandomLottoNumberGeneratorTest {

    private final RandomLottoNumberGenerator generator = new RandomLottoNumberGenerator();

    @Test
    @DisplayName("6개의 고유한 번호를 생성한다")
    void generateSixUniqueNumbers() {
        List<Integer> numbers = generator.generate();

        assertThat(numbers).hasSize(6);
        assertThat(new HashSet<>(numbers)).hasSize(6);
    }

    @Test
    @DisplayName("생성된 번호는 1부터 45 사이의 범위 내에 포함된다")
    void numbersAreWithinValidRange() {
        List<Integer> numbers = generator.generate();

        assertThat(numbers).allMatch(num -> num >= 1 && num <= 45);
    }
}
