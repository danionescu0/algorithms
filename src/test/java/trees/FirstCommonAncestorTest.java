package trees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirstCommonAncestorTest {

    @Test
    void getCommonAncestor() {
        var leaf5 = new Node<>(5);
        var leaf18 = new Node<>(18);
        var leaf2 = new Node<>(2);
        var node8 = new Node<>(8, leaf18, null);
        var node15 = new Node<>(15, leaf5, node8);
        var node20 = new Node<>(20, leaf2, null);
        var root = new Node<>(100, node15, node20);

        Assertions.assertEquals(root.getLeft(), node15);
        Assertions.assertNotEquals(root.getRight(), node15);
        Assertions.assertEquals(root.getLeft().getLeft(), leaf5);
        Assertions.assertNotEquals(root.getLeft().getRight(), leaf5);
        var sut = new FirstCommonAncestor<Integer>();
        var isNode15 = sut.getAncestor(root, leaf18, leaf5);
        Assertions.assertEquals(15, isNode15.getValue());
        var isNode100 = sut.getAncestor(root, leaf5, node20);
        Assertions.assertEquals(100, isNode100.getValue());
        var isNoNode = sut.getAncestor(root, leaf18, new Node<>(99));
        Assertions.assertNull(isNoNode);
    }
}