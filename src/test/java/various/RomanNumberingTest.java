import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class RomanNumberingTest {

    @org.junit.jupiter.api.Test
    void convertToRoman() {
        var romanNumbering = new RomanNumbering();

        Assertions.assertEquals("MCMXCIX", romanNumbering.convertToRoman(1999));
        Assertions.assertEquals("M", romanNumbering.convertToRoman(1000));
        Assertions.assertEquals("I", romanNumbering.convertToRoman(1));
        Assertions.assertEquals("MMMMM", romanNumbering.convertToRoman(5000));
        Assertions.assertEquals("DCCXCII", romanNumbering.convertToRoman(792));
    }
}