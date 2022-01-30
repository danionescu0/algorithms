package trees;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Design an algorithm and write the code to find the first common ancestor of two nodes in a binary tree.
 * Avoid storing additional nodes in the date structure. NOTE: This is not necessarily a binary search treee
 */
public class FirstCommonAncestor<T> {
    public Node<T> getAncestor(Node<T> rootNode, Node<T> first, Node<T> second) {
        var path1 = new Stack<Node<T>>();
        getPath(path1, rootNode, first);
        var path2 = new Stack<Node<T>>();
        getPath(path2, rootNode, second);
        var pathToFirst = new ArrayList<>(path1);
        var pathToSecond = new ArrayList<>(path2);
        for (var i=pathToFirst.size() - 1; i >= 0 ; i--) {
            for (var j=pathToSecond.size() - 1; j >= 0 ; j--) {
                if (pathToFirst.get(i) == pathToSecond.get(j)) {
                    return pathToFirst.get(i);
                }
            }
        }

        return null;
    }

    private boolean getPath(Stack<Node<T>> path, Node<T> current, Node<T> to) {
        path.push(current);
        if (current == to) {
            return true;
        }
        if (current.getLeft() != null) {
            if (getPath(path, current.getLeft(), to)) {
                return true;
            }
        }
        if (current.getRight() != null) {
            if (getPath(path, current.getRight(), to)) {
                return true;
            }
        }
        path.pop();

        return false;
    }
}