package various;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberToTextTest {
    @Test
    void convert() {
        var sut = new NumberToText();
        Assertions.assertEquals("seventeen thousand six hundred seventy-eight", sut.convert(17678));
        Assertions.assertEquals("four thousand three hundred fifty-one", sut.convert(4351));
        Assertions.assertEquals("thirteen", sut.convert(13));
        Assertions.assertEquals("nine hundred ninety-nine thousand nine hundred ninety-eight", sut.convert(999998));
    }
}