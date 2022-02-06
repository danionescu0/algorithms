package oop.apples;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public final class Apple {
    public enum Color {
        GREEN("green"),
        RED("red"),
        YELLOW("yellow"),
        ;
        private String name;

        Color(String name) {
            this.name = name;
        }
    }

    private final Integer id;
    private Integer weight;
    private Color color;

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