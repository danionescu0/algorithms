package strings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is a word or phrase
 * that is the same forwards and backwards. A permutation is a rearangement of letters. The palindrome does not need
 * to be limited to just dictionary words.
 *
 * EXAMPLE
 * Input: Tact Coa
 * Output: True (permutations "taco cat", "atco cta"..)
 *
 */
public class PalindromePermutation {
    private Map<String, Long> frequencies = new HashMap<>();

    public boolean isPermutation(String target) {
        var processed = target.toLowerCase().replaceAll("\\s", "");
        frequencies = processed.codePoints()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        var oddFrequencies = getOddNrOfFrequencies(frequencies);
        var evenFrequencies = frequencies.values().size() - oddFrequencies;
        if (processed.length() == 1) {
            return true;
        }
        if (oddFrequencies == frequencies.values().size() || evenFrequencies == frequencies.values().size()) {
            return true;
        }
        if (processed.length() % 2 == 0) {
            return oddFrequencies == frequencies.values().size();
        } else {
            return oddFrequencies > evenFrequencies && evenFrequencies % 2 != 0;
        }
    }

    private long getOddNrOfFrequencies(Map<String, Long> frequencies) {
        return frequencies
                .values()
                .stream()
                .map(freq -> freq % 2 == 0)
                .filter(aBoolean -> aBoolean)
                .count();

    }
}