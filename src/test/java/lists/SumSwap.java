package lists;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

/**
 * Given two arrays of integers, find a pair of values (one from each array) that you can swap to give the two
 * arrays the same sum
 *
 * Example:
 * Input:  {4, 1, 2, 1, 1, 2} and {3, 6, 3, 3}
 * Output {1, 3}
 */
public class SumSwap {
    @Data
    public static class Result {
        private int fromFirst;
        private int fromSecond;
        private boolean canSwap;

        public Result(int fromFirst, int fromSecond) {
            this.fromFirst = fromFirst;
            this.fromSecond = fromSecond;
            this.canSwap = true;
        }

        public Result(boolean canSwap) {
            this.canSwap = canSwap;
        }
    }

    public Result getSwap(int[] first, int[] second) {
        var firstSum = getSum(first);
        var secondSum = getSum(second);
        for (var i = 0; i < first.length; i++) {
            for (var j = 0; j < second.length; j++) {
                if (firstSum - first[i] + second[j] == secondSum - second[j] + first[i]) {
                    return new Result(first[i], second[j]);
                }
            }
        }

        return new Result(false);
    }

    private int getSum(int[] first) {
        return Arrays.stream(first).sum();
    }
}