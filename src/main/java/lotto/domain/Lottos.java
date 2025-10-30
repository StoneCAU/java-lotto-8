package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public int getCount() {
        return lottos.size();
    }

    public List<Lotto> toList() {
        return lottos;
    }

    public LottoResult calculateResult(Lotto winningLotto, int bonusNumber) {
        Map<LottoRank, Integer> rankCounts = lottos.stream()
                .map(lotto -> {
                    int matchCount = lotto.countMatches(winningLotto);
                    boolean hasBonus = lotto.has(bonusNumber);
                    return LottoRank.of(matchCount, hasBonus);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        rank -> rank,
                        Collectors.summingInt(rank -> 1)
                ));

        return new LottoResult(rankCounts);
    }
}
