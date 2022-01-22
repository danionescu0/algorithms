package various;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumbering {
    private Map<Integer, String> mappings = new LinkedHashMap<>();

    public RomanNumbering() {
        this.mappings.put(1000, "M");
        this.mappings.put(900, "CM");
        this.mappings.put(500, "D");
        this.mappings.put(400, "CD");
        this.mappings.put(100, "C");
        this.mappings.put(90, "XC");
        this.mappings.put(40, "XL");
        this.mappings.put(50, "L");
        this.mappings.put(10, "X");
        this.mappings.put(9, "IX");
        this.mappings.put(5, "V");
        this.mappings.put(4, "IV");
        this.mappings.put(1, "I");
    }

    public String convertToRoman(Integer number) {
        StringBuilder roman = new StringBuilder("");
        while (number > 0) {
            var currentNr = this.getMaximumPositiveFromRomanList(number);
            roman.append(this.mappings.get(currentNr));
            number -= currentNr;
        }

        return roman.toString();
    }

    private Integer getMaximumPositiveFromRomanList(Integer number) {
        if (number >= 1000) {
            return 1000;
        }

        return this.mappings
                .keySet()
                .stream()
                .filter(romanListNr -> romanListNr <= number)
                .findFirst()
                .orElse(1000);
    }
}