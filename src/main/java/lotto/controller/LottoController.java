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
        Money money = inputPurchaseAmount();
        Lottos lottos = purchaseLottos(money);

        Lotto winningLotto = inputWinningNumbers();
        int bonusNumber = inputBonusNumber(winningLotto);

        LottoResult result = lottos.calculateResult(winningLotto, bonusNumber);
        printResults(result, money);
    }

    private Money inputPurchaseAmount() {
        while (true) {
            try {
                String input = inputView.readLottoPurchaseAmount();
                int amount = InputParser.parseInt(input);
                return new Money(amount);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Lottos purchaseLottos(Money money) {
        Lottos lottos = lottoMachine.issue(money);
        outputView.printLottos(lottos);
        return lottos;
    }

    private Lotto inputWinningNumbers() {
        while (true) {
            try {
                String input = inputView.readWinningNumbers();
                List<Integer> numbers = InputParser.parseNumbers(input);
                return new Lotto(numbers);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private int inputBonusNumber(Lotto winningLotto) {
        while (true) {
            try {
                String input = inputView.readBonusNumber();
                int bonusNumber = InputParser.parseInt(input);
                BonusNumberValidator.validate(winningLotto, bonusNumber);
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void printResults(LottoResult result, Money money) {
        outputView.printResult(result);

        double profitRate = money.calculateProfitRate(result.getTotalPrize());
        outputView.printProfitRate(profitRate);
    }
}