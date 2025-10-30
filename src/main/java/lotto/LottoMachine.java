package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    public List<Lotto> publish(int ticketCount) {
        List<Lotto> tickets = new ArrayList<>();
        for(int i = 0; i < ticketCount; i++) {
            tickets.add(generateSingleTicket());
        }
        return tickets;
    }

    private Lotto generateSingleTicket() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }
}
