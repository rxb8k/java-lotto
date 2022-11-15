package lotto.domain;

import lotto.validator.Validator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
        Validator.checkUniqueNumber(numbers);
    }

    public void printNumbers() {
        System.out.println(numbers);
    }

    public int compareWithWinningNumber(List<Integer> winningNumber) {
        int sameNumberCount = 0;

        for (int i=0; i<46; i++) {
            if (numbers.contains(i) && winningNumber.contains(i)){
                sameNumberCount++;
            }
        }

        return sameNumberCount;
    }

    public boolean checkBonusNumber(int bonusNumber) {
        boolean containBonusNumber = false;
        if (numbers.contains(bonusNumber) == true) {
            containBonusNumber = true;
        }
        return containBonusNumber;
    }

    public Rank calculateRank(List<Integer> winningNumber, int bonusNumber) {
        int sameNumberCount = compareWithWinningNumber(winningNumber);
        boolean hasBonusNumber = checkBonusNumber(bonusNumber);

        if (sameNumberCount==6) {
            return Rank.FIRST_PLACE;
        } else if (sameNumberCount==5 && hasBonusNumber==true) {
            return Rank.SECOND_PLACE;
        } else if (sameNumberCount==5 && hasBonusNumber==false) {
            return Rank.THIRD_PLACE;
        } else if (sameNumberCount==4) {
            return Rank.FOURTH_PLACE;
        } else if (sameNumberCount==3) {
            return Rank.FIFTH_PLACE;
        }
        return Rank.NO_PLACE;
    }
}
