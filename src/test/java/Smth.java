import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class Smth {
    public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
        return queries.stream()
                .map(query ->
                        (int) strings.stream()
                                .map(s -> s.equals(query))
                                .filter(aBoolean -> aBoolean)
                                .count()
                )
                .collect(Collectors.toList());
    }


    @Test
    public void reverseTest() {
        var strings = Arrays.asList("ab", "ab", "abc");
        var queries = Arrays.asList("ab", "abc", "bc");
        Assertions.assertEquals(Arrays.asList(2, 1, 0), Smth.matchingStrings(strings, queries));
    }
}