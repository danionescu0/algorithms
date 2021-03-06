package strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromePermutationTest {

    @Test
    void isPermutation() {
        var pal = new PalindromePermutation();
        Assertions.assertTrue(pal.isPermutation("Tact Coa"));
        Assertions.assertTrue(pal.isPermutation("aauutty"));
        Assertions.assertTrue(pal.isPermutation("cim"));
    }

    @Test
    void isNotPermutation() {
        var pal = new PalindromePermutation();
        Assertions.assertFalse(pal.isPermutation("trala"));
    }

    @Test
    void cornerCases() {
        var pal = new PalindromePermutation();
        Assertions.assertTrue(pal.isPermutation("zzz"));
        Assertions.assertTrue(pal.isPermutation("aaaa"));

    }
}