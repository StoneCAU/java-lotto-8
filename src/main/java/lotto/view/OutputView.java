package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;

import java.util.List;

public class OutputView {

    private static final String NEW_Line = System.lineSeparator();
    private static final String LOTTO_PURCHASE_MESSAGE = "%d개를 구매했습니다.";

    public void printLottos(Lottos lottos) {
        printNewLine();
        System.out.printf(LOTTO_PURCHASE_MESSAGE, lottos.getCount());
        printNewLine();
        lottos.toList().forEach(this::printLotto);
    }

    private void printLotto(Lotto lotto) {
        List<Integer> sortedNumbers = lotto.getNumbers().stream()
                .sorted()
                .toList();
        System.out.println(sortedNumbers);
    }

    private void printNewLine() {
        System.out.print(NEW_Line);
    }
}
