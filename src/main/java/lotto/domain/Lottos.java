package lotto.domain;

import java.util.List;
import java.util.Map;
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

    public LottoResult calculateResult(Lotto winningLotto, BonusNumber bonusNumber) {
        Map<LottoRank, Integer> rankCounts = lottos.stream()
                .map(lotto -> evaluateRank(lotto, winningLotto, bonusNumber))
                .collect(Collectors.groupingBy(
                        rank -> rank,
                        Collectors.summingInt(rank -> 1)
                ));

        return new LottoResult(rankCounts);
    }

    private LottoRank evaluateRank(Lotto lotto, Lotto winningLotto, BonusNumber bonusNumber) {
        int match = lotto.countMatches(winningLotto);
        boolean hasBonus = lotto.has(bonusNumber.getValue());
        return LottoRank.of(match, hasBonus);
    }
}
