package lotto.parser;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    private static final String DELIMITER = ",";

    private InputParser() {
    }

    public static List<Integer> parseLottoNumbers(String input) {
        validateNotEmpty(input);

        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .map(InputParser::parseToInt)
                .toList();
    }

    public static int parseBonusNumber(String input) {
        validateNotEmpty(input);

        return parseToInt(input);
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력하세요.");
        }
    }

    private static void validateNotEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호를 입력해주세요.");
        }
    }
}
