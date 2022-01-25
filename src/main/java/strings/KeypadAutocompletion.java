package strings;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * On old cell phones users typed on a numeric keypad and the phone would provide a list of words that matched these
 * numbers. Each digit mapped to a set of 0 - 4 letters. Implement an algorithm to return a list of matching words,
 * given a sequence of digits. You are provided a list of valid words (provided a list of data structure you'd like).
 *
 */
public class KeypadAutocompletion {
    @Data
    @AllArgsConstructor
    public static class TreeNode {
        private String keyNumber;
        private HashSet<String> words = new HashSet<>();
        private HashSet<TreeNode> nodes = new HashSet<>();

        public TreeNode(String keyNumber) {
            this.keyNumber = keyNumber;
        }

        public TreeNode addNode(TreeNode node) {
            this.nodes.add(node);
            return this;
        }

        public TreeNode addWord(String word) {
            this.words.add(word);
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TreeNode treeNode = (TreeNode) o;
            return keyNumber.equals(treeNode.keyNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(keyNumber);
        }
    }

    private TreeNode rootNone = new TreeNode("0");
    private final HashMap<String, String> letterToKey = new HashMap<>();

    public KeypadAutocompletion() {
        addLetterKeys("abc", "2");
        addLetterKeys("def", "3");
        addLetterKeys("ghi", "4");
        addLetterKeys("jkl", "5");
        addLetterKeys("mno", "6");
        addLetterKeys("pqrs", "7");
        addLetterKeys("tuv", "8");
        addLetterKeys("wxyz", "9");
    }

    public void setDictionary(List<String> words) {
        words.forEach(this::addWordToDictionary);
    }

    public Set<String> predict(String keysPressed) {
        validateKeysPressed(keysPressed);
        var currentNode = rootNone;
        for (char ch: keysPressed.toCharArray()) {
            var key = String.valueOf(ch);
            var newNode = new TreeNode(key);
            System.out.println(newNode);
            if (currentNode.getNodes().contains(newNode)) {
                currentNode = currentNode
                        .getNodes()
                        .stream()
                        .filter(node -> node.equals(newNode))
                        .findFirst()
                        .get();
            } else {
                return new HashSet<>();
            }
        }

        return mergeWords(currentNode, new HashSet<String>());
    }

    private HashSet<String> mergeWords(TreeNode fromNode, HashSet<String> words) {
        words.addAll(fromNode.getWords());
        for (TreeNode node: fromNode.getNodes()) {
            words.addAll(mergeWords(node, words));
        }

        return words;
    }

    private void addWordToDictionary(String word) {
        var keys = wordToKeys(word);
        var currentNode = rootNone;
        for(String key: keys) {
            var newNode = new TreeNode(key);
            if (currentNode.getNodes().contains(newNode)) {
                currentNode = currentNode
                        .getNodes()
                        .stream()
                        .filter(node -> node.equals(newNode))
                        .findFirst()
                        .get();
                continue;
            }
            currentNode.addNode(newNode);
            currentNode = newNode;
        }
        currentNode.addWord(word);
    }

    private void addLetterKeys(String letters, String key) {
        for (char ch: letters.toCharArray()) {
            this.letterToKey.put(String.valueOf(ch), key);
        }
    }

    private List<String> wordToKeys(String word) {
        return word.chars()
                .mapToObj(value -> letterToKey.get(String.valueOf((char) value)))
                .collect(Collectors.toList());
    }

    private void validateKeysPressed(String keysPressed) {
        for (char ch: keysPressed.toCharArray()) {
            if (!Character.isDigit(ch)) {
                throw new IllegalArgumentException("Keys pressed must all be digits from 0-9");
            }
        }
    }
}