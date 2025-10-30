package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성
    @Test
    void 정상_로또는_6개_숫자_보유() {
        Lotto lotto = new Lotto(List.of(1, 3, 5, 7, 9, 11));
        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @Test
    void 숫자_6개_보다_적으면_예외() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    void 중복_있으면_예외() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    void 범위_벗어나면_예외() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    void 정렬되어_보관되는지_확인() {
        Lotto lotto = new Lotto(List.of(8, 2, 14, 3, 7, 1));
        assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 7, 8, 14);
    }

    @Test
    void 일치_개수_계산() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 7, 8, 14));
        int match = lotto.countMatchWith(List.of(1, 2, 3, 4, 5, 6));
        assertThat(match).isEqualTo(3);
    }

    @Test
    void 보너스_포함여부() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 7, 8, 14));
        assertThat(lotto.contains(7)).isTrue();
        assertThat(lotto.contains(9)).isFalse();
    }

}
