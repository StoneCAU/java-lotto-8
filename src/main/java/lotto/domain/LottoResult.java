package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> rankCounts;

    public LottoResult(Map<LottoRank, Integer> rankCounts) {
        this.rankCounts = initializeAllRanks(rankCounts);
    }

    private Map<LottoRank, Integer> initializeAllRanks(Map<LottoRank, Integer> rankCounts) {
        Map<LottoRank, Integer> result = new EnumMap<>(LottoRank.class);
        for (LottoRank rank : LottoRank.values()) {
            result.put(rank, rankCounts.getOrDefault(rank, 0));
        }
        return result;
    }

    public int getCountByRank(LottoRank rank) {
        return rankCounts.get(rank);
    }

    public int getTotalPrize() {
        return rankCounts.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }

    public Map<LottoRank, Integer> getRankCounts() {
        return rankCounts;
    }
}
