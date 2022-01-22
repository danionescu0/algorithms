package lists;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SingleLinkedList<T> {
    @Getter
    private Node<T> head;

    public SingleLinkedList(Node<T> head) {
        this.head = head;
    }

    public SingleLinkedList<T> appendNode(T data) {
        var newNode = new Node<T>(data);
        if (this.head == null) {
            this.head = newNode;
            return this;
        }
        var current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);

        return this;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        var current = head;
        buf.append(current.getData());
        buf.append(", ");
        while (current.getNext() != null) {
            current = current.getNext();
            buf.append(current.getData());
            buf.append(", ");
        }

        return String.format("SingleLinkedList {%s}", buf.toString());
    }
}