package lotto.parser;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    private static final String DELIMITER = ",";

    private InputParser() {
    }

    public static List<Integer> parseNumbers(String input) {
        validateNotEmpty(input);
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .map(InputParser::parseInt)
                .toList();
    }

    public static int parseInt(String input) {
        validateNotEmpty(input);

        try {
            return Integer.parseInt(input.trim());
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
