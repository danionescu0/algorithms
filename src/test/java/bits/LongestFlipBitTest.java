package bits;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestFlipBitTest {

    @Test
    void findLongestFlip() {
        var sut = new LongestFlipBit();
        Assertions.assertEquals(8, sut.findLongestFlip(1775)); //11011011011
        Assertions.assertEquals(4, sut.findLongestFlip(156)); // 10011100
        Assertions.assertEquals(7, sut.findLongestFlip(56789)); // 1101110111010101
        Assertions.assertEquals(2, sut.findLongestFlip(8)); // 1000
        Assertions.assertEquals(7, sut.findLongestFlip(8011)); // 1111101001011
    }
}