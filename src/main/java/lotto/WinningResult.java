package lotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningResult {
    private final Map<Rank, Integer> counts = new EnumMap<>(Rank.class);
    private final long totalPrize;
    private final double yieldPercent;

    public WinningResult(List<Lotto> tickets, WinningNumbers winningNumbers, int purchaseAmount) {
        initcounts();
        fillCounts(tickets, winningNumbers);
        this.totalPrize = calculateTotalPrize();
        this.yieldPercent = calculateYieldPercent(purchaseAmount, totalPrize);
    }

    private void initcounts() {
        for (Rank rank : Rank.values()) {
            counts.put(rank, 0);
        }
    }

    private void fillCounts(List<Lotto> tickets, WinningNumbers winningNumbers) {
        for(Lotto ticket : tickets) {
            Rank rank = evaluate(ticket, winningNumbers);
            counts.put(rank, counts.get(rank) + 1);
        }
    }

    private Rank evaluate(Lotto ticket, WinningNumbers winningNumbers) {
        int matchCount = ticket.countMatchWith(winningNumbers.getMainNumbers());
        boolean bonusMatched = ticket.contains(winningNumbers.getBonusNumber());
        return Rank.of(matchCount,bonusMatched);
    }

    private long calculateTotalPrize() {
        long sum = 0L;
        for (Map.Entry<Rank, Integer> entry : counts.entrySet()) {
            sum += entry.getKey().getPrize() * entry.getValue();
        }
        return sum;
    }

    private double calculateYieldPercent(int purchaseAmount, long totalPrize) {
        // (총 상금 / 구매금액) * 100
        return (double) totalPrize * 100 / purchaseAmount;
    }

    public Map<Rank, Integer> getCounts() {
        return counts;
    }

    public long getTotalPrize() {
        return totalPrize;
    }

    public double getYieldPercent() {
        return yieldPercent;
    }

}
