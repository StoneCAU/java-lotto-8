package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Money 클래스 테스트")
public class MoneyTest {

    @Nested
    @DisplayName("Money 생성 시")
    class CreateTest {

        @Test
        @DisplayName("양수 금액이 입력되면 정상적으로 생성된다")
        void createWhenPositiveAmount() {
            Money money = new Money(5000);
            assertThat(money.calculateLottoCount()).isEqualTo(5);
        }

        @Test
        @DisplayName("0 이하 금액이 입력되면 예외가 발생한다")
        void throwWhenAmountIsZeroOrNegative() {
            assertAll(
                    () -> assertThatThrownBy(() -> new Money(0))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessage("[ERROR] 금액은 양수여야 합니다."),
                    () -> assertThatThrownBy(() -> new Money(-1000))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessage("[ERROR] 금액은 양수여야 합니다.")
            );
        }

        @Test
        @DisplayName("1000원 단위가 아닌 금액이 입력되면 예외가 발생한다")
        void throwWhenAmountIsNotDivisibleBy1000() {
            assertThatThrownBy(() -> new Money(5500))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
    }

    @Nested
    @DisplayName("로또 개수 계산 시")
    class LottoCountTest {

        @Test
        @DisplayName("8000원을 입력하면 8개의 로또를 구매할 수 있다")
        void calculateLottoCountCorrectly() {
            Money money = new Money(8000);
            assertThat(money.calculateLottoCount()).isEqualTo(8);
        }
    }

    @Nested
    @DisplayName("수익률 계산 시")
    class ProfitRateTest {

        @Test
        @DisplayName("총 상금이 구입 금액보다 작을 때 수익률이 100% 미만으로 계산된다")
        void calculateProfitRateWhenLoss() {
            Money money = new Money(10000);
            double rate = money.calculateProfitRate(5000);
            assertThat(rate).isEqualTo(50.0);
        }

        @Test
        @DisplayName("총 상금이 구입 금액과 같을 때 수익률이 100%로 계산된다")
        void calculateProfitRateWhenBreakEven() {
            Money money = new Money(10000);
            double rate = money.calculateProfitRate(10000);
            assertThat(rate).isEqualTo(100.0);
        }

        @Test
        @DisplayName("총 상금이 구입 금액보다 클 때 수익률이 100% 초과로 계산된다")
        void calculateProfitRateWhenProfit() {
            Money money = new Money(10000);
            double rate = money.calculateProfitRate(20000);
            assertThat(rate).isEqualTo(200.0);
        }
    }
}
