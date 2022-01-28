package arrays;

import arrays.SumSwap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumSwapTest {
    @Test
    void getSwap() {
        var sumSwap = new SumSwap();
        var first = new int[]{4, 1, 2, 1, 1, 2};
        var second = new int[]{3, 6, 3, 3};
        var result = sumSwap.getSwap(first, second);
        assertTrue(result.isCanSwap());
        Assertions.assertEquals(4, result.getFromFirst());
        Assertions.assertEquals(6, result.getFromSecond());
    }

    @Test
    void getShortSwap() {
        var sumSwap = new SumSwap();
        var first = new int[]{5, 10};
        var second = new int[]{10, 5};
        var result = sumSwap.getSwap(first, second);
        assertTrue(result.isCanSwap());
        Assertions.assertEquals(5, result.getFromFirst());
        Assertions.assertEquals(5, result.getFromSecond());
    }

    @Test
    void getNoSwap() {
        var sumSwap = new SumSwap();
        var first = new int[]{1, 1, 1};
        var second = new int[]{2, 2, 2};
        var result = sumSwap.getSwap(first, second);
        assertFalse(result.isCanSwap());
    }
}