package lists;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

class MaxMinTest {
    private List<Integer> list = Arrays.asList(9, 8, 25, 2, 9, 33, 5);

    @Test
    void getVariant1() {
        var sut = new MaxMin();
        doTestGet(sut, sut::getVariant1);
    }

//    @Test
//    void getVariant2() {
//        var sut = new MaxMin();
//        doTestGet(sut, sut::getVariant2);
//    }

     <T, U, R> void doTestGet(MaxMin maxMin, BiFunction<Integer, List<Integer>, R> get) {
        Assertions.assertEquals(1, get.apply(3, list));
        Assertions.assertEquals(4, get.apply(4, list));
        Assertions.assertEquals(0, get.apply(2, list));
        Assertions.assertEquals(20, get.apply(3,  Arrays.asList(10, 100, 300, 200, 1000, 20, 30)));
    }
}