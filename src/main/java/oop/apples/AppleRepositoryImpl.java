package oop.apples;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implement an "apple" repository containing objects with id, weight and color
 * The repo should have the following methods
 * - get all
 * - get green apples
 * - get all sorted by weight
 * - get red and heavy
 * - get sorted by color asc and by weight desc
 * - get with weight above and limit
 */
public class AppleRepositoryImpl implements AppleRepository {
    private List<Apple> apples;

    public AppleRepositoryImpl(List<Apple> apples) {
        this.apples = apples;
    }

    @Override
    public List<Apple> getAll() {
        return this.apples;
    }

    public List<Apple> getGreen() {
        return this.apples
                .stream()
                .filter(apple -> apple.getColor() == Apple.Color.GREEN)
                .collect(Collectors.toList());
    }

    @Override
    public List<Apple> getSortedByWeight() {
        return this.apples
                .stream()
                .sorted((o1, o2) -> o1.getWeight().compareTo(o2.getWeight()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Apple> getRedAndHeavy() {
        return this.apples
                .stream()
                .filter(apple -> apple.getColor() == Apple.Color.RED)
                .filter(apple -> apple.getWeight() > 150)
                .collect(Collectors.toList());
    }

    @Override
    public List<Apple> getSortedByColorAscByWeightDesc() {
        return this.apples
                .stream()
                .sorted((o1, o2) -> {
                    if (o1.getColor().compareTo(o2.getColor()) == 0) {
                        return -o1.getWeight().compareTo(o2.getWeight());
                    } else {
                        return o1.getColor().compareTo(o2.getColor());
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Apple> getWithWeightAbove(int threshold, int limit) {
        return this.apples
                .stream()
                .filter(apple -> apple.getWeight() > threshold)
                .limit(limit)
                .collect(Collectors.toList());
    }
}
