package lotto.parser;

import java.util.Arrays;
import java.util.List;

public class LottoNumberParser {

    private static final String DELIMITER = ",";

    private LottoNumberParser() {
    }

    public static List<Integer> parse(String input) {
        validateNotEmpty(input);

        try {
            return Arrays.stream(input.split(DELIMITER))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 숫자여야 합니다.");
        }
    }

    private static void validateNotEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호를 입력해주세요.");
        }
    }
}
