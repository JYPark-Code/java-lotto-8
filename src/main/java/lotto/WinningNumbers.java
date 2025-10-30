package lotto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {
    private final List<Integer> mainNumbers;
    private final int bonusNumber;

    public WinningNumbers(String rawMainNumbers, String rawBonusNumber) {
        this.mainNumbers = parseMainNumbers(rawMainNumbers);
        this.bonusNumber = parseBonusNumber(rawBonusNumber);
        validateAll();
    }

    private List<Integer> parseMainNumbers(String rawMainNumbers) {
        try {
            return Arrays.stream(rawMainNumbers.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 쉼표로 구분된 숫자여야 합니다.");
        }
    }

    private int parseBonusNumber(String rawBonusNumber) {
        try {
            return Integer.parseInt(rawBonusNumber.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }
    }

    private void validateAll() {
        validateMainCount();
        validateRange(mainNumbers);
        validateNoDuplicate(mainNumbers);
        validateBonusRange();
        validateBonusNotDuplicated();
    }

    private void validateMainCount() {
        if (mainNumbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 정확히 6개여야 합니다.");
        }
    }

    private void validateRange(List<Integer> numbers){
        for (Integer n : numbers){
            validateRangeSingle(n);
        }
    }

    private void validateRangeSingle(int n) {
        if (n < 1 || n > 45) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.");
        }
    }

    private void validateNoDuplicate(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);
        if (set.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호에 중복된 숫자가 있습니다.");
        }
    }

    private void validateBonusRange() {
        validateRangeSingle(bonusNumber);
    }

    private void validateBonusNotDuplicated() {
        if (mainNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public List<Integer> getMainNumbers() {
        return mainNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }


}
