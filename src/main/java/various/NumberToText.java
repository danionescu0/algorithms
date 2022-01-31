package various;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Given any integer print an English phrase that describes the integer
 *
 * Example:
 * Input: 17678
 * Output: seventeen thousand six hundred seventy-eight
 */
public class NumberToText {
    private Map<Integer, String> numbersMap = new HashMap<>();
    private Map<Integer, String> twoDigits = new HashMap<>();

    public NumberToText() {
        this.numbersMap = Stream.of(new Object[][]{
            {0, "zero"}, {1, "one"}, {2, "two"}, {3, "three"}, {4, "four"}, {5, "five"},
            {6, "six"}, {7, "seven"}, {8, "eight"}, {9, "nine"}, {10, "ten"}, {11, "eleven"},
            {12, "twelve"}, {13, "thirteen"}, {14, "fourteen"}, {15, "fifteen"}, {16, "sixteen"}, {17, "seventeen"},
            {18, "eighteen"}, {19, "nineteen"}
        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));
        this.twoDigits = Stream.of(new Object[][]{
            {2, "twenty"}, {3, "thirty"}, {4, "forty"}, {5, "fifty"}, {6, "sixty"}, {7, "seventy"},
            {8, "eighty"}, {9, "ninety"}
        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));
    }

    public String convert(int number) {
        var signifParts = new ArrayList<String>();
        var converted = Integer.toString(number);
        var firstPartPoz = converted.length() % 3;
        if (firstPartPoz != 0) {
            signifParts.add(converted.substring(0, firstPartPoz));
        }
        for (var i=0;i < converted.length() / 3; i++) {
            signifParts.add(converted.substring(firstPartPoz + i * 3, firstPartPoz + (i+1) * 3));
        }
        return IntStream.range(0, signifParts.size())
                        .mapToObj(i -> {
                            var result = new StringBuilder();
                            result.append(convert3Digits(signifParts.get(i)));
                            if (signifParts.size() - i == 2) {
                                result.append(" thousand");
                            }
                            return result.toString();
                        })
                .collect(Collectors.joining(" "));
    }

    private String convert3Digits(String digits) {
        var result = new StringBuilder();
        if (digits.length() == 3) {
            result.append(this.numbersMap.get(Integer.parseInt(digits.substring(0, 1))));
            result.append(" hundred ");
            digits = digits.substring(1, 3);
        }
        var remainingNr = Integer.parseInt(digits);
        if (remainingNr < 20){
            result.append(this.numbersMap.get(remainingNr));
        } else {
            result.append(this.twoDigits.get(Integer.parseInt(digits.substring(0, 1))));
            result.append("-");
            result.append(this.numbersMap.get(Integer.parseInt(digits.substring(1, 2))));
        }

        return result.toString();
    }
}