package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        try {
            run();
        } finally {
            Console.close();
        }
    }

    private static void run() {
        PurchaseAmount purchaseAmount = readPurchaseAmount();     // 1) 금액 입력
        LottoMachine lottoMachine = new LottoMachine();
        int ticketCount = purchaseAmount.getTicketCount();
        List<Lotto> tickets = lottoMachine.publish(ticketCount);  // 2) 로또 발행

        WinningNumbers winningNumbers = readWinningNumbers();     // 3) 당첨/보너스 입력

        // ✅ 여기서 한 번에 출력 시작
        printPurchasedTickets(tickets, ticketCount);              // 4) 이제 발행 내역 출력
        WinningResult result = new WinningResult(
                tickets,
                winningNumbers,
                purchaseAmount.getAmount()
        );                                                        // 5) 결과 계산
        printResult(result);                                      // 6) 통계/수익률 출력
    }

    private static PurchaseAmount readPurchaseAmount() {
        while (true) {
            System.out.println("구입금액을 입력해 주세요.");
            try {
                return new PurchaseAmount(Console.readLine());
            } catch (IllegalArgumentException e) {
                printError(e);
            }
        }
    }

    private static WinningNumbers readWinningNumbers() {
        while (true) {
            try {
                System.out.println();
                System.out.println("당첨 번호를 입력해 주세요.");
                String main = Console.readLine();

                System.out.println();
                System.out.println("보너스 번호를 입력해 주세요.");
                String bonus = Console.readLine();

                return new WinningNumbers(main, bonus);
            } catch (IllegalArgumentException e) {
                printError(e);
            }
        }
    }

    private static void printPurchasedTickets(List<Lotto> tickets, int ticketCount) {
        System.out.println();
        System.out.println(ticketCount + "개를 구매했습니다.");
        for (Lotto lotto : tickets) {
            System.out.println(lotto.getNumbers());
        }
    }

    private static void printResult(WinningResult result) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        // 순서는 요구 예시에 맞게 출력
        printLine(result, Rank.FIFTH, "3개 일치 (5,000원)");
        printLine(result, Rank.FOURTH, "4개 일치 (50,000원)");
        printLine(result, Rank.THIRD, "5개 일치 (1,500,000원)");
        printLine(result, Rank.SECOND, "5개 일치, 보너스 볼 일치 (30,000,000원)");
        printLine(result, Rank.FIRST, "6개 일치 (2,000,000,000원)");

        double yield = result.getYieldPercent();
        // 소수점 둘째 자리에서 반올림 -> String.format("%.1f%%")
        System.out.println("총 수익률은 " + String.format("%.1f", yield) + "%입니다.");
    }

    private static void printLine(WinningResult result, Rank rank, String label) {
        int count = result.getCounts().get(rank);
        System.out.println(label + " - " + count + "개");
    }

    private static void printError(RuntimeException e) {
        System.out.println(e.getMessage());
    }

}
