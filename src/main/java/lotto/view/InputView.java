package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String LOTTO_PURCHASE_AMOUNT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_INPUT_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_INPUT_MESSAGE = "보너스 번호를 입력해 주세요.";

    public String readLottoPurchaseAmount() {
        System.out.println(LOTTO_PURCHASE_AMOUNT_INPUT_MESSAGE);
        return readLine();
    }

    public String readWinningNumbers() {
        printNewLine();
        System.out.println(WINNING_NUMBERS_INPUT_MESSAGE);
        return readLine();
    }

    public String readBonusNumber() {
        printNewLine();
        System.out.println(BONUS_NUMBER_INPUT_MESSAGE);
        return readLine();
    }

    private String readLine() {
        return Console.readLine();
    }

    private void printNewLine() {
        System.out.print(NEW_LINE);
    }
}
