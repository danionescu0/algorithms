package oop.apples;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public final class Apple {
    private final Integer id;
    private Integer weight;
    private String color;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apple)) return false;
        Apple apple = (Apple) o;
        return Objects.equals(id, apple.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}