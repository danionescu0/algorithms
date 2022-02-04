package linkedlists;

/**
 * Given tow (singly) linked lists, determine if the two lists intersect. Return the intersection node. note that the
 * intersection is defined based on reference. not value. That is, if the kth node of the first linked list is the exact
 * same node (by reference) as the jth node of the second linked list, then they are intersecting
 */
public class Intersection<T> {
    public Node<T> compute(Node<T> first, Node<T> second) {
        var currentFirst = first;
        while (first.getNext() != null) {
            var currentSecond = second;
                while (currentSecond.getNext() != null) {
                    if (currentFirst == currentSecond) {
                        return currentFirst;
                    }
                    currentSecond = currentSecond.getNext();
                }
            currentFirst = currentFirst.getNext();
        }

        return null;
    }
}