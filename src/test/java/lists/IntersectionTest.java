package lists;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntersectionTest {

    @Test
    void computeAndFound() {
        var node1_4 = new Node<>(6);
        var node1_3 = new Node<>(3, node1_4);
        var node1_2 = new Node<>(9, node1_3);
        var node1_1 = new Node<>(1, node1_2);

        var node2_3 = new Node<>(8, node1_3);
        var node2_2 = new Node<>(6, node2_3);
        var node2_1 = new Node<>(2, node2_2);

        var node3_1 = new Node<>(11, node1_1);

        var sut = new Intersection<Integer>();
        var firstIntersection = sut.compute(node1_1, node2_1);
        Assertions.assertEquals(node2_3.getNext(), node1_3);
        Assertions.assertEquals(3, firstIntersection.getData());
        var secondIntersection = sut.compute(node1_1, node3_1);
        Assertions.assertEquals(1, secondIntersection.getData());
    }

    @Test
    void computeAndNone() {
        var node1_1 = new Node<>(6);
        var node2_1 = new Node<>(2);

        var sut = new Intersection<Integer>();
        var result = sut.compute(node1_1, node2_1);
        Assertions.assertNull(result);
    }
}