package stacks;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Stack;

/**
 * Write a program to sort a stack such that smallest items are on the top. You can use an additional temporary
 * stack, but you may not copy the elements into any other data structure (such as an array). The stack supports the
 * following operations: push, pup, peek and is Empty
 */
public class SortStack<T extends Comparable<T>> {
    @Data
    @AllArgsConstructor
    private static class Occuring<T> {
        private T element;
        private int nrOccurances;
    }

    public Stack<T> sort(Stack<T> input) {
        var pushTo = new Stack<T>();
        while (!input.isEmpty()) {
            var occuring = getSmallestItem(input, pushTo);
            rebalanceItems(input, pushTo, occuring);
        }

        return pushTo;
    }

    private Occuring<T> getSmallestItem(Stack<T> input, Stack<T> pushTo) {
        var min = input.peek();
        var nrSecondStack = pushTo.size();
        while (!input.isEmpty()) {
            if (input.peek().compareTo(min) < 0) {
                min = input.peek();
            }
            pushTo.push(input.pop());
        }
        var count = 0;
        var putBackNr = pushTo.size() - nrSecondStack;
        var i = 0;
        while (!pushTo.isEmpty() && i < putBackNr) {
            if (pushTo.peek().compareTo(min) == 0) {
                count++;
            }
            input.push(pushTo.pop());
            i++;
        }

        return new Occuring<>(min, count);
    }

    private Stack<T> rebalanceItems(Stack<T> input, Stack<T> pushTo, Occuring<T> occuring) {
        for (var i=0; i<occuring.nrOccurances; i++) {
            pushTo.push(occuring.element);
        }
        var nrSecondStack = pushTo.size();
        while (!input.isEmpty()) {
            var curentElement = input.pop();
            if (curentElement != occuring.element) {
                pushTo.push(curentElement);
            }
        }
        var putBackNr = pushTo.size() - nrSecondStack;
        for (var i=0; i < putBackNr; i++) {
            input.push(pushTo.pop());
        }

        return input;
    }
}