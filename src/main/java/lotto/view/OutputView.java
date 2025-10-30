package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;

import java.util.List;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String PURCHASE_HEADER = NEW_LINE + "%d개를 구매했습니다." + NEW_LINE;
    private static final String RESULT_HEADER = NEW_LINE + "당첨 통계";
    private static final String SEPARATOR = "---";
    private static final String RANK_FORMAT = "%s (%,d원) - %d개";
    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %.1f%%입니다.";

    public void printLottos(Lottos lottos) {
        printPurchaseHeader(lottos.getCount());
        printAllLottos(lottos);
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

    public void printResult(LottoResult result) {
        System.out.println(RESULT_HEADER);
        System.out.println(SEPARATOR);

        for (LottoRank rank : LottoRank.values()) {
            printRankResult(rank, result.getCountByRank(rank));
        }
    }

    private void printRankResult(LottoRank rank, int count) {
        System.out.printf(RANK_FORMAT + NEW_LINE,
                rank.getDescription(),
                rank.getPrizeMoney(),
                count);
    }

    public void printProfitRate(double profitRate) {
        System.out.printf(PROFIT_RATE_FORMAT + NEW_LINE, profitRate);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}