package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @Test
    @DisplayName("1부터 45 사이의 유효한 숫자로 LottoNumber를 생성한다")
    void createValidLottoNumber() {
        LottoNumber lottoNumber = new LottoNumber(1);

        assertThat(lottoNumber.value()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("1~45 범위를 벗어나면 예외가 발생한다")
    void throwWhenNumberOutOfRange(int invalidNumber) {
        assertThatThrownBy(() -> new LottoNumber(invalidNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @Test
    @DisplayName("같은 값을 가진 LottoNumber는 동일하다")
    void equalityCheck() {
        LottoNumber lottoNumber = new LottoNumber(5);
        LottoNumber sameNumber = new LottoNumber(5);

        assertThat(lottoNumber).isEqualTo(sameNumber);
    }
}
