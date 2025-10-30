package lotto.domain;

public enum LottoRank {

    FIRST(6, 2_000_000_000, "6개 일치"),
    SECOND(5, 30_000_000, "5개 일치, 보너스 볼 일치"),
    THIRD(5, 1_500_000, "5개 일치"),
    FOURTH(4, 50_000, "4개 일치"),
    FIFTH(3, 5_000, "3개 일치");

    private final int matchCount;
    private final int prizeMoney;
    private final String description;

    LottoRank(int matchCount, int prizeMoney, String description) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.description = description;
    }

    public static LottoRank of(int matchCount, boolean hasBonus) {
        if (matchCount == 5 && hasBonus) {
            return SECOND;
        }

        for (LottoRank rank : values()) {
            if (rank.matchCount == matchCount && rank != SECOND) {
                return rank;
            }
        }

        return null;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public String getDescription() {
        return description;
    }
}
