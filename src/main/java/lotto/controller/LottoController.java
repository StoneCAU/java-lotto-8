package lotto.controller;

import lotto.domain.LottoMachine;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

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
        // 1. 구매
        String lottoPurchaseAmount = inputView.readLottoPurchaseAmount();
        Money money = new Money(lottoPurchaseAmount);
        Lottos lottos = lottoMachine.issue(money);
        outputView.printLottos(lottos);
    }
}
