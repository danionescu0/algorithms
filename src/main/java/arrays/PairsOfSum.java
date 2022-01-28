package arrays;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Design an algorithm to find all pairs of integers within an array which sum to a specified value
 *
 * Example:
 * Input: {1, 5, 3, 7, 9, 20, 11} and 12
 * Output: (5, 7), (3, 9), (1, 11)
 */
public class PairsOfSum {
    @Data
    @AllArgsConstructor
    public static class Pair {
        private int first;
        private int second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair pair = (Pair) o;
            return (first == pair.first && second == pair.second) || (first == pair.second && second == pair.first);
        }

        @Override
        public int hashCode() {
            return Objects.hash(Math.min(first, second), Math.max(first, second));
        }
    }

    public Set<Pair> getPairs(int[] input, int value) {
        var unique = Arrays.stream(input)
                .boxed()
                .collect(Collectors.toSet());

        return Arrays.stream(input)
                .mapToObj(elem -> unique.contains(value - elem) ? new Pair(elem, value - elem) : null)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}