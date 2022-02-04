package lists;

import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * You will be given a list of integers, arr , and a single integer.
 * You must create an array of length  from elements of  such that its unfairness is minimized.
 * Call that array arr'. Unfairness of an array is calculated as max(arr') - min(arr')
 *
 * Where:
 * - max denotes the largest integer in arr'
 * - min denotes the smallest integer in arr'
 *
 * Example:
 * arr = [1, 4, 7, 2]
 * k = 2
 * arr' = [4, 7] , 7 - 4 = 3
 *
 * Constranints:
 * n < 10^5
 * 2 < k < n
 * 0 < arr[i] <= 10^9
 */
public class MaxMin {
    //@todo optimize code, the sorting makes it inefficient
    public static int get(int k, List<Integer> input) {
        if (input.size() < k) {
            throw new IllegalArgumentException("K must me less then input list size");
        }
        Collections.sort(input);
        var minUnfairness = Integer.MAX_VALUE;
        for(var i=0; i < input.size() - k; i++) {
            var min = Integer.MAX_VALUE;
            var max = Integer.MIN_VALUE;
            for (var j= i; j < i + k; j++) {
                min = Math.min(min, input.get(j));
                max = Math.max(max, input.get(j));
            }
            minUnfairness = Math.min(minUnfairness, max - min);
        }

        return minUnfairness;
    }
}