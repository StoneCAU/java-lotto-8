package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("LottoMachine 테스트")
public class LottoMachineTest {

    @Nested
    @DisplayName("로또 발행 시")
    class IssueTest {

        @Test
        @DisplayName("구입 금액에 따라 올바른 수량의 로또를 발행한다")
        void issueCorrectNumberOfLottos() {
            LottoMachine machine = new LottoMachine(new FixedLottoNumberGenerator(List.of(1, 2, 3, 4, 5, 6)));
            Lottos lottos = machine.issue(new Money(5000));

            assertThat(lottos.getCount()).isEqualTo(5);
        }

        @Test
        @DisplayName("각 로또는 6개의 고유 번호를 가진다")
        void eachLottoHasSixUniqueNumbers() {
            LottoMachine machine = new LottoMachine(new FixedLottoNumberGenerator(List.of(1, 2, 3, 4, 5, 6)));
            Lottos lottos = machine.issue(new Money(3000));

            for (Lotto lotto : lottos.toList()) {
                List<Integer> numbers = lotto.getSortedNumbers();
                assertThat(numbers)
                        .hasSize(6)
                        .doesNotHaveDuplicates();
            }
        }

        @Test
        @DisplayName("사용자 정의 번호 생성기를 주입하면 해당 생성기로 발행된다")
        void issueWithCustomGenerator() {
            LottoNumberGenerator customGenerator = () -> List.of(10, 11, 12, 13, 14, 15);
            LottoMachine machine = new LottoMachine(customGenerator);
            Lottos lottos = machine.issue(new Money(1000));

            List<Integer> numbers = lottos.toList().getFirst().getSortedNumbers();
            assertThat(numbers).containsExactly(10, 11, 12, 13, 14, 15);
        }
    }
}
