package lists;

import lombok.Data;

/**
 * Write the code to remove duplicates from an unsorted list
 * FOLLOW UP
 * How would you solve this problem if a temporary buffer is not allowed?
 */
public class RemoveDuplicates<T> {
    public SingleLinkedList<T> removeDuplicates(SingleLinkedList<T> list) {
        var head = list.getHead();
        var fixed = head;
        while (fixed != null && fixed.getNext() != null) {
            var moving = fixed.getNext();
            var beforeMoving = fixed;
            System.out.println(fixed.getData() + " ... " + moving.getData());
            if (fixed.getData().equals(moving.getData())) {
//                System.out.println("-> " + beforeMoving.getData() + " ... " + moving.getData());
                if (moving.getNext() == null) {
                    beforeMoving.setNext(null);
                } else {
                    beforeMoving.setNext(moving.getNext().getNext());
                    moving = beforeMoving;
                }
            }
            while (moving.getNext() != null) {
                beforeMoving = moving;
                moving = moving.getNext();
//                System.out.println(fixed.getData() + " ... " + moving.getData());
                if (fixed.getData().equals(moving.getData())) {
//                    System.out.println("-> " + beforeMoving.getData() + " ... " + moving.getData());
                    beforeMoving.setNext(moving.getNext());
                }
            }
            fixed = fixed.getNext();
        }

        return new SingleLinkedList<>(head);
    }
}