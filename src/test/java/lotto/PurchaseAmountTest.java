package lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {

    @Test
    void 금액이_1000원_단위면_티켓_개수를_계산한다() {
        PurchaseAmount pa = new PurchaseAmount("14000");
        assertThat(pa.getAmount()).isEqualTo(14000);
        assertThat(pa.getTicketCount()).isEqualTo(14);
    }

    @Test
    void 숫자가_아니면_예외() {
        assertThatThrownBy(() -> new PurchaseAmount("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    void 지불금액_1000원_미만이면_예외() {
        assertThatThrownBy(() -> new PurchaseAmount("500"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    void 지불금액_1000원_단위가_아니면_예외() {
        assertThatThrownBy(() -> new PurchaseAmount("1300"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

}
