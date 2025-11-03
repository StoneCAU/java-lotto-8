package lotto.domain;

import lotto.parser.InputParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottosTest {

    private static final Lotto WINNING_LOTTO = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    private static final BonusNumber BONUS = new BonusNumber(7, WINNING_LOTTO);

    @Test
    @DisplayName("유효한 로또 목록이면 정상 생성된다")
    void createSuccessfully() {
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(7, 8, 9, 10, 11, 12))
        ));

        assertThat(lottos).isNotNull();
        assertThat(lottos.getCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("toList()는 불변 리스트를 반환한다")
    void toListReturnsUnmodifiableList() {
        Lottos lottos = new Lottos(List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6))));

        assertThatThrownBy(() ->
                lottos.toList().add(new Lotto(List.of(7, 8, 9, 10, 11, 12)))
        ).isInstanceOf(UnsupportedOperationException.class);
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6', FIRST",
            "'1,2,3,4,5,7', SECOND",
            "'1,2,3,4,5,45', THIRD",
            "'1,2,3,4,44,45', FOURTH",
            "'1,2,3,43,44,45', FIFTH"
    })
    @DisplayName("로또 번호에 따라 올바른 등수를 반환한다")
    void calculateCorrectRank(String numbersStr, LottoRank expectedRank) {
        List<Integer> numbers = InputParser.parseNumbers(numbersStr);
        Lottos lottos = new Lottos(List.of(new Lotto(numbers)));

        LottoResult result = lottos.calculateResult(WINNING_LOTTO, BONUS);

        assertThat(result.getCountByRank(expectedRank)).isEqualTo(1);
    }

    @Test
    @DisplayName("3개 미만 일치 시 NONE(꽝)")
    void noneRank() {
        Lottos lottos = new Lottos(List.of(new Lotto(List.of(1, 10, 20, 30, 40, 45))));

        LottoResult result = lottos.calculateResult(WINNING_LOTTO, BONUS);

        assertThat(result.getTotalPrize()).isZero();
    }

    @Test
    @DisplayName("여러 로또 결과를 모두 집계한다")
    void aggregateMultipleResults() {
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),   // 1등
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),   // 2등
                new Lotto(List.of(1, 2, 3, 4, 5, 45)),  // 3등
                new Lotto(List.of(1, 2, 3, 4, 44, 45))  // 4등
        ));

        LottoResult result = lottos.calculateResult(WINNING_LOTTO, BONUS);

        assertAll(
                () -> assertThat(result.getCountByRank(LottoRank.FIRST)).isEqualTo(1),
                () -> assertThat(result.getCountByRank(LottoRank.SECOND)).isEqualTo(1),
                () -> assertThat(result.getCountByRank(LottoRank.THIRD)).isEqualTo(1),
                () -> assertThat(result.getCountByRank(LottoRank.FOURTH)).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("모든 로또가 꽝이면 총 상금은 0")
    void totalPrizeIsZeroWhenAllLose() {
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(10, 11, 12, 13, 14, 15)),
                new Lotto(List.of(20, 21, 22, 23, 24, 25))
        ));

        LottoResult result = lottos.calculateResult(WINNING_LOTTO, BONUS);

        assertThat(result.getTotalPrize()).isZero();
    }
}
