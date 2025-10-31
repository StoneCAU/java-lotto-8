package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Lottos 테스트")
public class LottosTest {

    @Nested
    @DisplayName("Lottos 생성 시")
    class CreateTest {

        @Test
        @DisplayName("유효한 로또 목록 전달 시 정상적으로 생성된다")
        void createWhenLottosAreValid() {
            Lottos lottos = new Lottos(List.of(
                    new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                    new Lotto(List.of(7, 8, 9, 10, 11, 12))
            ));

            assertThat(lottos).isNotNull();
        }
    }

    @Nested
    @DisplayName("로또 컬렉션 검증 시")
    class CollectionTest {

        @Test
        @DisplayName("getCount()는 보유한 로또 개수를 반환한다")
        void getCountReturnsCorrectValue() {
            Lottos lottos = new Lottos(List.of(
                    new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                    new Lotto(List.of(7, 8, 9, 10, 11, 12))
            ));
            assertThat(lottos.getCount()).isEqualTo(2);
        }

        @Test
        @DisplayName("toList()는 불변 리스트를 반환한다")
        void toListReturnsImmutableList() {
            Lottos lottos = new Lottos(List.of(
                    new Lotto(List.of(1, 2, 3, 4, 5, 6))
            ));
            assertThatThrownBy(() -> lottos.toList().add(new Lotto(List.of(7, 8, 9, 10, 11, 12))))
                    .isInstanceOf(UnsupportedOperationException.class);
        }
    }

    @Nested
    @DisplayName("당첨 결과 계산 시")
    class ResultTest {

        private final Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        private static final int BONUS_NUMBER = 7;

        @Test
        @DisplayName("6개 번호가 일치하면 1등으로 계산된다")
        void calculateFirstRank() {
            Lottos lottos = new Lottos(List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6))));
            LottoResult result = lottos.calculateResult(winningLotto, BONUS_NUMBER);
            assertThat(result.getCountByRank(LottoRank.FIRST)).isEqualTo(1);
        }

        @Test
        @DisplayName("5개 + 보너스 번호 일치 시 2등으로 계산된다")
        void calculateSecondRank() {
            Lottos lottos = new Lottos(List.of(new Lotto(List.of(1, 2, 3, 4, 5, 7))));
            LottoResult result = lottos.calculateResult(winningLotto, BONUS_NUMBER);
            assertThat(result.getCountByRank(LottoRank.SECOND)).isEqualTo(1);
        }

        @Test
        @DisplayName("5개만 일치 시 3등으로 계산된다")
        void calculateThirdRank() {
            Lottos lottos = new Lottos(List.of(new Lotto(List.of(1, 2, 3, 4, 5, 45))));
            LottoResult result = lottos.calculateResult(winningLotto, BONUS_NUMBER);
            assertThat(result.getCountByRank(LottoRank.THIRD)).isEqualTo(1);
        }

        @Test
        @DisplayName("4개 일치 시 4등으로 계산된다")
        void calculateFourthRank() {
            Lottos lottos = new Lottos(List.of(new Lotto(List.of(1, 2, 3, 4, 44, 45))));
            LottoResult result = lottos.calculateResult(winningLotto, BONUS_NUMBER);
            assertThat(result.getCountByRank(LottoRank.FOURTH)).isEqualTo(1);
        }

        @Test
        @DisplayName("3개 일치 시 5등으로 계산된다")
        void calculateFifthRank() {
            Lottos lottos = new Lottos(List.of(new Lotto(List.of(1, 2, 3, 43, 44, 45))));
            LottoResult result = lottos.calculateResult(winningLotto, BONUS_NUMBER);
            assertThat(result.getCountByRank(LottoRank.FIFTH)).isEqualTo(1);
        }

        @Test
        @DisplayName("3개 미만 일치 시 NONE으로 계산된다")
        void calculateNoneRank() {
            Lottos lottos = new Lottos(List.of(new Lotto(List.of(1, 10, 20, 30, 40, 45))));
            LottoResult result = lottos.calculateResult(winningLotto, BONUS_NUMBER);
            assertThat(result.getTotalPrize()).isEqualTo(0);
        }

        @Test
        @DisplayName("여러 로또의 결과를 집계하면 각 등수별 개수가 정확히 계산된다")
        void calculateMultipleResults() {
            Lottos lottos = new Lottos(List.of(
                    new Lotto(List.of(1, 2, 3, 4, 5, 6)),   // 1등
                    new Lotto(List.of(1, 2, 3, 4, 5, 7)),   // 2등
                    new Lotto(List.of(1, 2, 3, 4, 5, 45)),  // 3등
                    new Lotto(List.of(1, 2, 3, 4, 44, 45))  // 4등
            ));

            LottoResult result = lottos.calculateResult(winningLotto, BONUS_NUMBER);

            assertAll(
                    () -> assertThat(result.getCountByRank(LottoRank.FIRST)).isEqualTo(1),
                    () -> assertThat(result.getCountByRank(LottoRank.SECOND)).isEqualTo(1),
                    () -> assertThat(result.getCountByRank(LottoRank.THIRD)).isEqualTo(1),
                    () -> assertThat(result.getCountByRank(LottoRank.FOURTH)).isEqualTo(1)
            );
        }

        @Test
        @DisplayName("모든 로또가 꽝인 경우 총 상금은 0이다")
        void totalPrizeIsZeroWhenAllLose() {
            Lottos lottos = new Lottos(List.of(
                    new Lotto(List.of(10, 11, 12, 13, 14, 15)),
                    new Lotto(List.of(20, 21, 22, 23, 24, 25))
            ));
            LottoResult result = lottos.calculateResult(winningLotto, BONUS_NUMBER);
            assertThat(result.getTotalPrize()).isEqualTo(0);
        }
    }
}
