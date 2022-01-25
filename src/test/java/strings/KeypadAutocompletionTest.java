package strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KeypadAutocompletionTest {
    @Test
    public void predictMulti() {
        var autocomplete = new KeypadAutocompletion();
        autocomplete.setDictionary(Arrays.asList("anual", "ana", "antic", "bleg"));
        var result = autocomplete.predict("26");
        Assertions.assertEquals(Arrays.asList("ana", "anual", "antic"), new ArrayList<String>(result));
        var result2 = autocomplete.predict("268");
        Assertions.assertEquals(List.of("anual", "antic"), new ArrayList<String>(result2));
        var result3 = autocomplete.predict("2682");
        Assertions.assertEquals(List.of("anual"), new ArrayList<String>(result3));
    }

    @Test
    public void predictOne() {
        var autocomplete = new KeypadAutocompletion();
        autocomplete.setDictionary(Arrays.asList("anual", "ana", "antic", "bleg"));
        var result = autocomplete.predict("2682");
        Assertions.assertEquals(List.of("anual"), new ArrayList<String>(result));
    }

    @Test
    public void predictEmpty() {
        var autocomplete = new KeypadAutocompletion();
        autocomplete.setDictionary(Arrays.asList("x", "ana", "antic", "bleg"));
        var result = autocomplete.predict("2611");
        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void illegalKeypressed() {
        var autocomplete = new KeypadAutocompletion();
        autocomplete.setDictionary(Arrays.asList("x", "ana", "antic", "bleg"));
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            autocomplete.predict("R09");
        });
        assertTrue(exception.getMessage().contains("Keys pressed must all be digits from 0-9"));
    }

    @Test
    public void treeNode() {
        var node0 = new KeypadAutocompletion.TreeNode("0");
        var node1 = new KeypadAutocompletion.TreeNode("6");
        node0.addNode(node1);
        var node2 = new KeypadAutocompletion.TreeNode("2");
        var node3 = new KeypadAutocompletion.TreeNode("8");
        node1.addNode(node2);
        node1.addNode(node3);
        Assertions.assertTrue(node0.getNodes().contains(new KeypadAutocompletion.TreeNode("6")));
        Assertions.assertTrue(node1.getNodes().contains(new KeypadAutocompletion.TreeNode("2")));
    }
}