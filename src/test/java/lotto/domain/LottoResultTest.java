package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoResultTest {

    @Test
    @DisplayName("일부 등수만 전달돼도 나머지 등수는 0으로 초기화된다")
    void initializeMissingRanksAsZero() {
        Map<LottoRank, Integer> partial = Map.of(LottoRank.FIRST, 1, LottoRank.SECOND, 2);
        LottoResult result = new LottoResult(partial);

        assertAll(
                () -> assertThat(result.getCountByRank(LottoRank.FIRST)).isEqualTo(1),
                () -> assertThat(result.getCountByRank(LottoRank.SECOND)).isEqualTo(2),
                () -> assertThat(result.getCountByRank(LottoRank.THIRD)).isZero(),
                () -> assertThat(result.getCountByRank(LottoRank.FIFTH)).isZero(),
                () -> assertThat(result.getCountByRank(LottoRank.NONE)).isZero()
        );
    }

    @Test
    @DisplayName("등수별 개수를 정확히 반환한다")
    void getCountByRankReturnsCorrectValue() {
        Map<LottoRank, Integer> counts = new EnumMap<>(LottoRank.class);
        counts.put(LottoRank.FIRST, 1);
        counts.put(LottoRank.SECOND, 3);
        counts.put(LottoRank.FOURTH, 2);

        LottoResult result = new LottoResult(counts);

        assertAll(
                () -> assertThat(result.getCountByRank(LottoRank.FIRST)).isEqualTo(1),
                () -> assertThat(result.getCountByRank(LottoRank.SECOND)).isEqualTo(3),
                () -> assertThat(result.getCountByRank(LottoRank.FOURTH)).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("존재하지 않는 등수는 0을 반환한다")
    void returnZeroForMissingRank() {
        Map<LottoRank, Integer> counts = new EnumMap<>(LottoRank.class);
        counts.put(LottoRank.FIRST, 1);

        LottoResult result = new LottoResult(counts);

        assertThat(result.getCountByRank(LottoRank.SECOND)).isZero();
    }

    @Test
    @DisplayName("등수별 상금을 모두 합산해 총 상금을 계산한다")
    void calculateTotalPrizeCorrectly() {
        Map<LottoRank, Integer> counts = new EnumMap<>(LottoRank.class);
        counts.put(LottoRank.FIRST, 1);
        counts.put(LottoRank.FIFTH, 2);

        LottoResult result = new LottoResult(counts);

        int expectedTotal = 2_000_000_000 + 10_000;
        assertThat(result.getTotalPrize()).isEqualTo(expectedTotal);
    }

    @Test
    @DisplayName("모든 등수가 0이면 총 상금은 0이다")
    void totalPrizeIsZeroWhenAllZero() {
        LottoResult result = new LottoResult(new EnumMap<>(LottoRank.class));

        assertThat(result.getTotalPrize()).isZero();
    }
}
