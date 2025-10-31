package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("LottoRank 테스트")
public class LottoRankTest {

    @Nested
    @DisplayName("of() 메서드 호출 시")
    class OfMethodTest {

        @Test
        @DisplayName("6개 일치하면 FIRST를 반환한다")
        void returnFirstWhenSixMatches() {
            assertThat(LottoRank.of(6, false)).isEqualTo(LottoRank.FIRST);
        }

        @Test
        @DisplayName("5개와 보너스 번호 일치 시 SECOND를 반환한다")
        void returnSecondWhenFiveAndBonus() {
            assertThat(LottoRank.of(5, true)).isEqualTo(LottoRank.SECOND);
        }

        @Test
        @DisplayName("5개만 일치 시 THIRD를 반환한다")
        void returnThirdWhenFiveWithoutBonus() {
            assertThat(LottoRank.of(5, false)).isEqualTo(LottoRank.THIRD);
        }

        @Test
        @DisplayName("4개 일치 시 FOURTH를 반환한다")
        void returnFourthWhenFourMatches() {
            assertThat(LottoRank.of(4, false)).isEqualTo(LottoRank.FOURTH);
        }

        @Test
        @DisplayName("3개 일치 시 FIFTH를 반환한다")
        void returnFifthWhenThreeMatches() {
            assertThat(LottoRank.of(3, false)).isEqualTo(LottoRank.FIFTH);
        }

        @Test
        @DisplayName("3개 미만 일치 시 NONE을 반환한다")
        void returnNoneWhenLessThanThreeMatches() {
            assertThat(LottoRank.of(2, false)).isEqualTo(LottoRank.NONE);
        }
    }

    @Nested
    @DisplayName("등수 정보 조회 시")
    class InfoTest {

        @Test
        @DisplayName("FIRST 등수의 상금과 설명이 올바르다")
        void firstPrizeInfo() {
            assertAll(
                    () -> assertThat(LottoRank.FIRST.getPrizeMoney()).isEqualTo(2_000_000_000),
                    () -> assertThat(LottoRank.FIRST.getDescription()).isEqualTo("6개 일치")
            );
        }

        @Test
        @DisplayName("SECOND 등수의 상금과 설명이 올바르다")
        void secondPrizeInfo() {
            assertAll(
                    () -> assertThat(LottoRank.SECOND.getPrizeMoney()).isEqualTo(30_000_000),
                    () -> assertThat(LottoRank.SECOND.getDescription()).isEqualTo("5개 일치, 보너스 볼 일치")
            );
        }

        @Test
        @DisplayName("THIRD 등수의 상금과 설명이 올바르다")
        void thirdPrizeInfo() {
            assertAll(
                    () -> assertThat(LottoRank.THIRD.getPrizeMoney()).isEqualTo(1_500_000),
                    () -> assertThat(LottoRank.THIRD.getDescription()).isEqualTo("5개 일치")
            );
        }

        @Test
        @DisplayName("FOURTH 등수의 상금과 설명이 올바르다")
        void fourthPrizeInfo() {
            assertAll(
                    () -> assertThat(LottoRank.FOURTH.getPrizeMoney()).isEqualTo(50_000),
                    () -> assertThat(LottoRank.FOURTH.getDescription()).isEqualTo("4개 일치")
            );
        }

        @Test
        @DisplayName("FIFTH 등수의 상금과 설명이 올바르다")
        void fifthPrizeInfo() {
            assertAll(
                    () -> assertThat(LottoRank.FIFTH.getPrizeMoney()).isEqualTo(5_000),
                    () -> assertThat(LottoRank.FIFTH.getDescription()).isEqualTo("3개 일치")
            );
        }

        @Test
        @DisplayName("NONE 등수의 상금과 설명이 올바르다")
        void nonePrizeInfo() {
            assertAll(
                    () -> assertThat(LottoRank.NONE.getPrizeMoney()).isEqualTo(0),
                    () -> assertThat(LottoRank.NONE.getDescription()).isEqualTo("꽝")
            );
        }
    }
}
