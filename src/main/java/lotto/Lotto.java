package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = sortedCopy(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        validateNoDuplicate(numbers);
        validateRange(numbers);
    }

    private void validateNoDuplicate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (Integer n : numbers) {
            if (n < 1 || n > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이 숫자여야 합니다.");
            }
        }
    }

    private List<Integer> sortedCopy(List<Integer> src) {
        List<Integer> copy = new ArrayList<>(src);
        Collections.sort(copy);
        return copy;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int countMatchWith(List<Integer> winningNumbers) {
        int count = 0;
        for (Integer n : numbers) {
            if (winningNumbers.contains(n)) {
                count++;
            }
        }
        return count;
    }

    public boolean contains(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }


}
