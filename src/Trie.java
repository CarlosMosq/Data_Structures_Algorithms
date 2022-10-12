import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Trie {
    private static class Node{
        private final char letter;
        private final HashMap<Character, Node> children = new HashMap<>();
        private boolean isEndOfWord = false;

        public Node(char letter) {
            this.letter = letter;
        }

        public void setEndOfWord(boolean endOfWord) {
            isEndOfWord = endOfWord;
        }
        public boolean isEndOfWord() {
            return isEndOfWord;
        }
        public HashMap<Character, Node> getChildren() {
            return children;
        }
        public char getLetter() {
            return letter;
        }

        private boolean hasChild(char letter) {
            return getChildren().containsKey(letter);
        }
        private void addChild(char letter) {
            getChildren().put(letter, new Node(letter));
        }
        private Node getChild(char letter) {
            return getChildren().get(letter);
        }
        private void removeChild(char letter) {
            getChildren().remove(letter);
        }

        private Node[] getAllChildren() {
            return getChildren().values().toArray(new Node[0]);
        }
    }

    private final Node root = new Node(Character.MIN_VALUE);

    public void insert(String word) {
        Node current = root;
        for(char letter : word.toLowerCase().toCharArray()) {
            if(!current.hasChild(letter)) {
                current.addChild(letter);
            }
            current = current.getChild(letter);
        }
        current.setEndOfWord(true);
    }

    public boolean contains(String word) {
        if(word == null) return false;
        Node current = root;
        for(char letter : word.toLowerCase().toCharArray()) {
            if(!current.hasChild(letter)) return false;
            current = current.getChild(letter);
        }
        return current.isEndOfWord();
    }

    public void remove(String word) {
        if(word == null) return;
        remove(root, word, 0);
    }

    private void remove(Node root, String word, int index) {
        if(!contains(word)) return;
        if(index == word.length()) {
            root.setEndOfWord(false);
            return;
        }
        var ch = word.charAt(index);
        var child = root.getChild(ch);
        if(child == null) return;

        remove(child, word, index + 1);

        if(child.getChildren().isEmpty() && !child.isEndOfWord()) {
            root.removeChild(ch);
        }
    }

    public void traversePreOrder() {
        traversePreOrder(root);
    }

    private void traversePreOrder(Node root) {
        System.out.println(root.getLetter());
        for(Node child : root.getAllChildren()) {
            traversePreOrder(child);
        }
    }

    public void traversePostOrder() {
        traversePostOrder(root);
    }

    private void traversePostOrder(Node root) {
        for(Node child : root.getAllChildren()) {
            traversePostOrder(child);
        }
        System.out.println(root.getLetter());
    }

    public List<String> autoComplete(String word) {
        List<String> list = new ArrayList<>();
        var current = findLastNode(word);
        autoComplete(current, word, list);
        return list;
    }


    private void autoComplete(Node root, String word, List<String> words) {
        if(root == null) return;

        if(root.isEndOfWord()) words.add(word);

        for(var child : root.getAllChildren()) {
            autoComplete(child, word + child.getLetter(), words);
        }
    }

    private Node findLastNode(String prefix) {
        if(prefix == null) return null;
        var current = root;
        for(char letter : prefix.toCharArray()) {
            var child = current.getChild(letter);
            if (child == null) return null;
            current = child;
        }
        return current;
    }


}
