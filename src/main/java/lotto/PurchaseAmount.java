package lotto;

public class PurchaseAmount {
    private static final int UNIT_PRICE = 1000;
    private final int amount;

    public PurchaseAmount(String rawInput) {
        this.amount = parseAmount(rawInput);
        validateMultipleofUnit();
    }

    public int getAmount() {
        return amount;
    }

    public int getTicketCount() {
        return amount / UNIT_PRICE;
    }

    private int parseAmount(String rawInput){
        try {
            return Integer.parseInt(rawInput.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }

    private void validateMultipleofUnit() {
        if (amount < UNIT_PRICE) {
            throw new IllegalArgumentException("[ERROR] 최소 구입 금액은 1000원 입니다.");
        }
        if (amount % UNIT_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위입니다.");
        }
    }
}
