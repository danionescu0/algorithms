package stacks;

import lombok.Data;

@Data
public class StackNode<T> {
    private T data;
    private StackNode<T> next;

    public StackNode(T data) {
        this.data = data;
    }
}