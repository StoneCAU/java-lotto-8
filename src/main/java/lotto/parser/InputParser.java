package lotto.parser;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    private static final String DELIMITER = ",";

    private static final String ERROR_EMPTY_INPUT = "[ERROR] 입력값을 입력해주세요.";
    private static final String ERROR_EMPTY_TOKEN = "[ERROR] 빈 번호는 입력할 수 없습니다.";
    private static final String ERROR_NOT_NUMBER = "[ERROR] 로또 번호는 숫자만 입력 가능합니다.";
    private static final String ERROR_SINGLE_NOT_NUMBER = "[ERROR] 숫자를 입력해야 합니다.";

    private InputParser() {}

    public static List<Integer> parseNumbers(String input) {
        validateNotEmpty(input);
        List<String> tokens = splitTokens(input);
        validateTokens(tokens);
        return convertToIntegers(tokens);
    }

    public static int parseSingleNumber(String input) {
        validateNotEmpty(input);
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_SINGLE_NOT_NUMBER);
        }
    }

    private static void validateNotEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ERROR_EMPTY_INPUT);
        }
    }

    private static List<String> splitTokens(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .toList();
    }

    private static void validateTokens(List<String> tokens) {
        if (tokens.isEmpty() || hasEmptyToken(tokens)) {
            throw new IllegalArgumentException(ERROR_EMPTY_TOKEN);
        }
    }

    private static boolean hasEmptyToken(List<String> tokens) {
        return tokens.stream().anyMatch(String::isEmpty);
    }

    private static List<Integer> convertToIntegers(List<String> tokens) {
        try {
            return tokens.stream()
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER);
        }
    }
}
