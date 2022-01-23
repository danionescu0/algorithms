package stacks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class SortStackTest {

    @Test
    void sortMisc() {
        var stack = new Stack<Integer>();
        stack.addAll(Arrays.asList(5, 9, 2, 12, 2, 7));
        var stackSorting = new SortStack<Integer>();
        var sorted = stackSorting.sort(stack);
        assertStack(Arrays.asList(2, 2, 5, 7, 9, 12), sorted);
    }

    @Test
    void sortOne() {
        var stack = new Stack<Integer>();
        stack.addAll(Arrays.asList(5));
        var stackSorting = new SortStack<Integer>();
        var sorted = stackSorting.sort(stack);
        assertStack(Arrays.asList(5), sorted);
    }

    @Test
    void sortSame() {
        var stack = new Stack<Integer>();
        stack.addAll(Arrays.asList(5, 5, 5));
        var stackSorting = new SortStack<Integer>();
        var sorted = stackSorting.sort(stack);
        assertStack(Arrays.asList(5, 5, 5), sorted);
    }

    private <T> void assertStack(List<T> expected, Stack<T> stack) {
        var stackConverted = new ArrayList<>(stack);
        Assertions.assertEquals(expected.size(), stack.size());
        for(var i=0;i<expected.size();i++) {
            Assertions.assertEquals(expected.get(i), stackConverted.get(i));
        }
    }
}