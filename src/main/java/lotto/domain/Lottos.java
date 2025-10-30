package lotto.domain;

import java.util.List;

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
}
