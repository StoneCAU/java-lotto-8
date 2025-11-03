package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @Test
    @DisplayName("양수 금액이 입력되면 정상적으로 생성된다")
    void createWhenPositiveAmount() {
        Money money = new Money(5000);

        assertThat(money.calculateLottoCount()).isEqualTo(5);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1000})
    @DisplayName("0 이하 금액이 입력되면 예외가 발생한다")
    void throwWhenAmountIsZeroOrNegative(int amount) {
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 금액은 양수여야 합니다.");
    }

    @Test
    @DisplayName("1000원 단위가 아닌 금액이 입력되면 예외가 발생한다")
    void throwWhenAmountIsNotDivisibleBy1000() {
        assertThatThrownBy(() -> new Money(5500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
    }

    @Test
    @DisplayName("구입 금액에 따라 로또 개수를 정확히 계산한다")
    void calculateLottoCountCorrectly() {
        Money money = new Money(8000);

        assertThat(money.calculateLottoCount()).isEqualTo(8);
    }

    @ParameterizedTest
    @CsvSource({
            "10_000, 5_000, 50.0",
            "10_000, 10_000, 100.0",
            "10_000, 20_000, 200.0"
    })
    @DisplayName("총 상금과 구입 금액에 따라 수익률을 정확히 계산한다")
    void calculateProfitRate(int purchaseAmount, int prize, double expectedRate) {
        Money money = new Money(purchaseAmount);

        double rate = money.calculateProfitRate(prize);

        assertThat(rate).isEqualTo(expectedRate);
    }
}
