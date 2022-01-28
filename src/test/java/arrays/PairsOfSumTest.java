package arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PairsOfSumTest {

    @Test
    void getPairs() {
        var sut = new PairsOfSum();
        var result = new ArrayList<>(sut.getPairs(new int[]{1, 5, 3, 7, 9, 20, 11}, 12));
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(5, result.get(0).getFirst());
        Assertions.assertEquals(7, result.get(0).getSecond());
        Assertions.assertEquals(3, result.get(1).getFirst());
        Assertions.assertEquals(9, result.get(1).getSecond());
        Assertions.assertEquals(1, result.get(2).getFirst());
        Assertions.assertEquals(11, result.get(2).getSecond());
    }

    @Test
    void noPairs() {
        var sut = new PairsOfSum();
        var result = sut.getPairs(new int[]{1, 2, 3}, 9);
        Assertions.assertEquals(0, result.size());
    }

    @Test
    void noElements() {
        var sut = new PairsOfSum();
        var result = sut.getPairs(new int[]{}, 9);
        Assertions.assertEquals(0, result.size());
    }
}