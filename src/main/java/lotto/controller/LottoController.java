package lotto.controller;

import lotto.domain.*;
import lotto.parser.InputParser;
import lotto.validator.BonusNumberValidator;
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
        String purchaseAmountInput = inputView.readLottoPurchaseAmount();
        int purchaseAmount = InputParser.parseInt(purchaseAmountInput);
        Money money = new Money(purchaseAmount);
        Lottos lottos = lottoMachine.issue(money);
        outputView.printLottos(lottos);

        // 당첨 번호 입력
        String winningNumbersInput = inputView.readWinningNumbers();
        List<Integer> winningNumbers = InputParser.parseNumbers(winningNumbersInput);
        Lotto winningLotto = new Lotto(winningNumbers);

        // 보너스 번호 입력
        String bonusNumberInput = inputView.readBonusNumber();
        int bonusNumber = InputParser.parseInt(bonusNumberInput);
        BonusNumberValidator.validate(winningLotto, bonusNumber);

        // 당첨 내역 출력
        LottoResult result = lottos.calculateResult(winningLotto, bonusNumber);
        outputView.printResult(result);

        // 수익률 출력
        double profitRate = money.calculateProfitRate(result.getTotalPrize());
        outputView.printProfitRate(profitRate);
    }
}
