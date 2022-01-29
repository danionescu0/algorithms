package arrays;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Given an array of integers, write a method to find indices m and n such that if you sorted elements m through
 * n the entire array would be sorted. Minimize n -m (that is, find the smallest such sequence)
 *
 * Example:
 * Input: 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19
 * Output(3, 9)
 */
public class SubSort {
    @Data
    @AllArgsConstructor
    public class Result {
        private int first;
        private int last;
    }

    public Result getSub(Integer[] elements) {
        var sorted = Arrays.stream(elements).sorted().collect(Collectors.toList());
        var start = elements.length + 1;
        var end = -1;
        for (var i=0; i < elements.length; i++) {
            if (!elements[i].equals(sorted.get(i))) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return end == -1 ? new Result(0, 0) : new Result(start, end);
    }
}