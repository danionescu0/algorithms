package oop.apples;

import java.util.List;

public interface AppleRepository {
    List<Apple> getAll();
    List<Apple> getGreen();
    List<Apple> getSortedByWeight();
    List<Apple> getRedAndHeavy();
    List<Apple> getSortedByColorAscByWeightDesc();
    List<Apple> getWithWeightAbove(int threshold, int limit);
}