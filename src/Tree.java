import java.util.ArrayList;
import java.util.Stack;

public class Tree {

    private static class Node {
        private final int value;
        private Node leftChild;
        private Node rightChild;

        public Node(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
        public Node getLeftChild() {
            return leftChild;
        }
        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }
        public Node getRightChild() {
            return rightChild;
        }
        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }
    }

    private Node root;
    private int count;
    public Node getRoot() {
        return root;
    }

    public void insert(int value) {
        if(root == null) {
            root = new Node(value);
            count++;
            return;
        }
        var current = root;
        while(current != null) {
            if(value > current.getValue()) {
                if(current.getRightChild() == null) {
                    current.setRightChild(new Node(value));
                    count++;
                    break;
                }
                else {
                    current = current.getRightChild();
                }
            }
            else if(value < current.getValue()) {
                if (current.getLeftChild() == null) {
                    current.setLeftChild(new Node(value));
                    count++;
                    break;
                }
                else {
                    current = current.getLeftChild();
                }
            }
            else return;
        }
    }

    public boolean find(int value) {
        if(root == null) return false;

        var current = root;
        while(current != null) {
            if(value == current.getValue()) return true;
            else if (value > current.getValue()) {
                current = current.getRightChild();
            }
            else if(value < current.getValue()) {
                current = current.getLeftChild();
            }
        }
        return false;
    }

    public void print() {
        Stack<Node> stack = new Stack<>();
        ArrayList<Integer> values = new ArrayList<>();
        if (root == null) {
            return;
        }
        stack.add(root);
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            values.add(node.value);
            if (node.getLeftChild() != null) {
                stack.add(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                stack.add(node.getRightChild());
            }
        }
        System.out.println(values.toString());
    }

    public void traversePreOrder(Node root) {
        if (root == null) return;
        System.out.println(root.getValue());
        traversePreOrder(root.getLeftChild());
        traversePreOrder(root.getRightChild());
    }

    public void traverseInOrder(Node root) {
        if (root == null) return;
        traverseInOrder(root.getLeftChild());
        System.out.println(root.getValue());
        traverseInOrder(root.getRightChild());
    }

    public void traversePostOrder(Node root) {
        if(root == null) return;
        traversePostOrder(root.getLeftChild());
        traversePostOrder(root.getRightChild());
        System.out.println(root.getValue());
    }

    public void traverseDescInOrder(Node root) {
        if (root == null) return;
        traverseDescInOrder(root.getRightChild());
        System.out.println(root.getValue());
        traverseDescInOrder(root.getLeftChild());
    }

    public void traverseBreadthFirst() {
        for(int i = 0; i <= height(root); i++) {
            printKDistance(i);
        }
    }

    public int height(Node root) {
        if(root == null) return -1;
        if(root.getLeftChild() == null && root.getRightChild() == null) return 0;
        return 1 + Math.max(height(root.getLeftChild()), height(root.getRightChild()));
    }

    public boolean isEqual(Tree tree) {
        if (tree == null) return false;
        return isEqual(getRoot(), tree.getRoot());
    }

    private boolean isEqual(Node root, Node node) {
        if(root == null && node == null) return true;
        else if(root != null && node != null) return root == node
                && isEqual(root.getLeftChild(), node.getLeftChild())
                && isEqual(root.getRightChild(), node.getRightChild());
        return false;
    }

    public boolean validate() {
        return validate(getRoot(), Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean validate(Node root, int min, int max) {
        if(root ==  null) return true;
        if(root.getValue() < min || root.getValue() > max) return false;

        return validate(root.getLeftChild(), min, root.getValue() - 1)
                && validate(root.getRightChild(), root.getValue() + 1, max);

    }

    public void printKDistance(int distance) {
        printKDistance(root, distance);
    }

    private void printKDistance(Node node, int distance) {
        if(node == null) return;
        if(distance == 0) {
            System.out.println(node.getValue());
            return;
        };
        printKDistance(node.getLeftChild(), distance - 1);
        printKDistance(node.getRightChild(), distance - 1);
    }

    public int size() {
        return count;
    }

    public int countLeaves() {
        ArrayList<Integer> generalUse = new ArrayList<>();
        countLeaves(root, generalUse);
        return generalUse.size();
    }

    private void countLeaves(Node node, ArrayList<Integer> generalUse) {
        if(node == null) return;
        if(node.getLeftChild() == null && node.getRightChild() == null) generalUse.add(node.getValue());
        countLeaves(node.getLeftChild(), generalUse);
        countLeaves(node.getRightChild(), generalUse);
    }

    public int max() {
        return max(root);
    }

    public int max(Node node) {
        if(node == null) return 0;
        if(node.getRightChild() == null) return node.getValue();
        return max(node.getRightChild());
    }

    public boolean contains(int e) {
        return contains(root, e);
    }

    private boolean contains(Node node, int e) {
        if(node == null) return false;
        return node.getValue() == e || contains(node.getLeftChild(), e) || contains(node.getRightChild(), e);
    }


}
