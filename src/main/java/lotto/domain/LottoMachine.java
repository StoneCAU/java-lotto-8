package lotto.domain;

import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {

    private final LottoNumberGenerator generator;

    public LottoMachine() {
        this.generator = new RandomLottoNumberGenerator();
    }

    public Lottos issue(Money purchaseAmount) {
        int count = purchaseAmount.calculateLottoCount();

        List<Lotto> lottos = IntStream.range(0, count)
                .mapToObj(i -> new Lotto(generator.generate()))
                .toList();

        return new Lottos(lottos);
    }

}
