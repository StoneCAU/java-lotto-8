package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoRankTest {

    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, false, FOURTH",
            "3, false, FIFTH",
            "2, false, NONE",
            "1, false, NONE",
            "0, false, NONE"
    })
    @DisplayName("일치 개수와 보너스 여부에 따라 올바른 등수를 반환한다")
    void returnCorrectRank(int matchCount, boolean hasBonus, LottoRank expected) {
        assertThat(LottoRank.of(matchCount, hasBonus)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "FIRST, 2_000_000_000, '6개 일치'",
            "SECOND, 30_000_000, '5개 일치, 보너스 볼 일치'",
            "THIRD, 1_500_000, '5개 일치'",
            "FOURTH, 50_000, '4개 일치'",
            "FIFTH, 5_000, '3개 일치'",
            "NONE, 0, '꽝'"
    }, delimiter = ',')
    @DisplayName("각 등수는 올바른 상금과 설명을 가진다")
    void hasCorrectPrizeAndDescription(LottoRank rank, int prizeMoney, String description) {
        assertAll(
                () -> assertThat(rank.getPrizeMoney()).isEqualTo(prizeMoney),
                () -> assertThat(rank.getDescription()).isEqualTo(description)
        );
    }
}
