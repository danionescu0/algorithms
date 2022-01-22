package lists;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RemoveDuplicatesTest {

    @Test
    void singleLinkedList() {
        var list = new SingleLinkedList<Integer>();
        list.appendNode(5);
        list.appendNode(5);
        var head = list.getHead();
        var next = head.getNext();
        head.setNext(null);
        System.out.println(list);
    }

    @Test
    void removeOneDuplicate() {
        var list = getSmallList();
        var sut = new RemoveDuplicates<Integer>();
        var result = sut.removeDuplicates(list);
        checkList(Arrays.asList(5, 8, 3, 1), result);
    }

    @Test
    void removeManyDuplicates() {
        var list = new SingleLinkedList<Integer>();
        list.appendNode(5);
        list.appendNode(8);
        list.appendNode(5);
        list.appendNode(8);
        list.appendNode(5);
        var sut = new RemoveDuplicates<Integer>();
        var result = sut.removeDuplicates(list);
        System.out.println(result);

        checkList(Arrays.asList(5, 8), result);
    }

    @Test
    void removeAllButOne() {
        var list = new SingleLinkedList<Integer>();
        list.appendNode(5);
        list.appendNode(5);
        list.appendNode(5);
        var sut = new RemoveDuplicates<Integer>();
        var result = sut.removeDuplicates(list);
        System.out.println(result);
        checkList(Arrays.asList(5), result);
    }

    private SingleLinkedList<Integer> getSmallList() {
        var list = new SingleLinkedList<Integer>();
        list.appendNode(5);
        list.appendNode(8);
        list.appendNode(3);
        list.appendNode(5);
        list.appendNode(1);

        return list;
    }

    private <T> void checkList(List<T> expected, SingleLinkedList<T> list) {
        var head = list.getHead();
        var current = head;
        Assertions.assertEquals(expected.get(0), head.getData());
        var currentIter = 1;
        while (current.getNext() != null) {
            current = current.getNext();
            Assertions.assertEquals(expected.get(currentIter), current.getData());
            currentIter++;
        }
    }
}