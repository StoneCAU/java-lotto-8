package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;

import java.util.List;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String PURCHASE_HEADER = NEW_LINE + "%d개를 구매했습니다." + NEW_LINE;

    public void printLottos(Lottos lottos) {
        printPurchaseHeader(lottos.getCount());
        printAllLottos(lottos);
        printNewLine();
    }

    private void printPurchaseHeader(int count) {
        System.out.printf(PURCHASE_HEADER, count);
    }

    private void printAllLottos(Lottos lottos) {
        lottos.toList().forEach(this::printLotto);
    }

    private void printLotto(Lotto lotto) {
        List<Integer> sortedNumbers = lotto.getNumbers().stream()
                .sorted()
                .toList();
        System.out.println(sortedNumbers);
    }

    private void printNewLine() {
        System.out.print(NEW_LINE);
    }
}
