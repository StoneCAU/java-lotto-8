package lotto.controller;

import lotto.domain.LottoMachine;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.parser.LottoNumberParser;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public LottoController(InputView inputView, OutputView outputView, LottoMachine lottoMachine) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachine = lottoMachine;
    }

    public void run() {
        // 로또 구매
        String lottoPurchaseAmount = inputView.readLottoPurchaseAmount();
        Money money = new Money(lottoPurchaseAmount);
        Lottos lottos = lottoMachine.issue(money);
        outputView.printLottos(lottos);

        // 당첨 번호 입력
        String winningNumbersInput = inputView.readWinningNumbers();
        List<Integer> winningNumbers = LottoNumberParser.parse(winningNumbersInput);


    }
}
