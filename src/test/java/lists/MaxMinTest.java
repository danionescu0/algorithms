package lists;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MaxMinTest {
    @Test
    void get() {
        var sut = new MaxMin();
        var list = Arrays.asList(9, 8, 25, 2, 9, 33, 5);
        Assertions.assertEquals(1, sut.get(3, list));
        Assertions.assertEquals(4, sut.get(4, list));
        Assertions.assertEquals(0, sut.get(2, list));
        Assertions.assertEquals(20, sut.get(3, Arrays.asList(10, 100, 300, 200, 1000, 20, 30)));
    }
}