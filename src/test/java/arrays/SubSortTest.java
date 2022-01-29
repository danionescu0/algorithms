package arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SubSortTest {

    @Test
    void getSub() {
        var sut = new SubSort();
        var result = sut.getSub(new Integer[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19});
        Assertions.assertEquals(result.getFirst(), 3);
        Assertions.assertEquals(result.getLast(), 9);
    }

    @Test
    void noSequence() {
        var sut = new SubSort();
        var result = sut.getSub(new Integer[]{1, 2, 3});
        Assertions.assertEquals(result.getFirst(), 0);
        Assertions.assertEquals(result.getLast(), 0);
    }

    @Test
    void allSequence() {
        var sut = new SubSort();
        var result = sut.getSub(new Integer[]{3, 1, 2});
        Assertions.assertEquals(result.getFirst(), 0);
        Assertions.assertEquals(result.getLast(), 2);
    }
}