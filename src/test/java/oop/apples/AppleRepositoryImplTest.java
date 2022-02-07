package oop.apples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AppleRepositoryImplTest {
    @Test
    void getGreen() {
        var sut = new AppleRepositoryImpl(getDummy());
        var result = sut.getGreen();
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(1, result.get(0).getId());
        Assertions.assertEquals(2, result.get(1).getId());
    }

    @Test
    void getSortedByWeight() {
        var sut = new AppleRepositoryImpl(getDummy());
        var result = sut.getSortedByWeight();
        Assertions.assertEquals(5, result.size());
        Assertions.assertEquals(Arrays.asList(1, 4, 2, 3, 5), getIds(result));
    }

    @Test
    void getRedAngHeavy() {
        var sut = new AppleRepositoryImpl(getDummy());
        var result = sut.getRedAndHeavy();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(3, result.get(0).getId());
    }

    @Test
    void getSortedByColorAscByWeightDesc() {
        var sut = new AppleRepositoryImpl(getDummy());
        var result = sut.getSortedByColorAscByWeightDesc();
        Assertions.assertEquals(5, result.size());
        Assertions.assertEquals(Arrays.asList(2, 1, 3, 5, 4), getIds(result));
    }

    @Test
    void getWithWeightAbove() {
        var sut = new AppleRepositoryImpl(getDummy());
        var result = sut.getWithWeightAbove(100, 3);
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(Arrays.asList(2, 3, 4), getIds(result));
    }

    List<Integer> getIds(List<Apple> apples) {
        return apples.stream()
                .map(Apple::getId)
                .collect(Collectors.toList());
    }


    private List<Apple> getDummy() {
        var apples = new ArrayList<Apple>();
        apples.add(new Apple(1, 80, Apple.Color.GREEN));
        apples.add(new Apple(2, 120, Apple.Color.GREEN));
        apples.add(new Apple(3, 155, Apple.Color.RED));
        apples.add(new Apple(4, 110, Apple.Color.YELLOW));
        apples.add(new Apple(5, 175, Apple.Color.YELLOW));

        return apples;
    }
}