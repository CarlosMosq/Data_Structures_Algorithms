import java.util.ArrayList;
import java.util.Stack;

public class AVLTree {
    public class AVLNode{
        private AVLNode leftNode;
        private AVLNode rightNode;
        private int value;
        private int height;
        public AVLNode(int value) {
            this.value = value;
        }
        public AVLNode getLeftNode() {
            return leftNode;
        }
        public void setLeftNode(AVLNode leftNode) {
            this.leftNode = leftNode;
        }
        public AVLNode getRightNode() {
            return rightNode;
        }
        public void setRightNode(AVLNode rightNode) {
            this.rightNode = rightNode;
        }
        public int getValue() {
            return value;
        }
        public void setValue(int value) {
            this.value = value;
        }
        public int getHeight() {
            return height;
        }
        public void setHeight(int height) {
            this.height = height;
        }
    }

    private AVLNode root;

    public void insert(int number) {
        root = insert(root, number);
    }
    private AVLNode insert(AVLNode root, int number) {
        if(root == null) {
            root = new AVLNode(number);
        }
        else if(number > root.getValue()) {
            root.setRightNode(insert(root.getRightNode(), number));
        }
        else
            root.setLeftNode(insert(root.getLeftNode(), number));

        root.setHeight(height(root));

        root = determineRotations(root);

        return root;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }
    private boolean isBalanced(AVLNode root) {
        return !isRightHeavy(root) && !isLeftHeavy(root);
    }

    public boolean isPerfect(AVLNode root) {
        // you didn't do this one
        return true;
    }

    private AVLNode determineRotations(AVLNode node) {
        AVLNode rotated = node;
        if(balance(node.getRightNode()) > 0) {
            node.setRightNode(rightRotate(node.getRightNode()));
            rotated = leftRotate(node);
        }
        else if(balance(node.getRightNode()) < 0) {
            rotated = leftRotate(node);
        }
        else if(balance(node.getLeftNode()) > 0) {
            rotated = rightRotate(node);
        }
        else if(balance(node.getLeftNode()) < 0){
            node.setLeftNode(leftRotate(node.getLeftNode()));
            rotated = rightRotate(node);
        }
        return rotated;
    }

    private AVLNode rightRotate(AVLNode node) {
        AVLNode newRoot = node.getLeftNode();
        node.setLeftNode(newRoot.getRightNode());
        newRoot.setRightNode(node);
        node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())) + 1);
        newRoot.setHeight(Math.max(height(newRoot.getLeftNode()), height(newRoot.getRightNode())) + 1);
        return newRoot;
    }

    private AVLNode leftRotate(AVLNode node) {
        AVLNode newRoot = node.getRightNode();
        node.setRightNode(newRoot.getLeftNode());
        newRoot.setLeftNode(node);
        node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())) + 1);
        newRoot.setHeight(Math.max(height(newRoot.getLeftNode()), height(newRoot.getRightNode())) + 1);
        return newRoot;
    }

    private boolean isLeftHeavy(AVLNode node) {
        return balance(node) > 1;
    }

    private boolean isRightHeavy(AVLNode node) {
        return balance(node) < -1;
    }

    private int balance(AVLNode node) {
        return node == null? 0 : height(node.getLeftNode()) - height(node.getRightNode());
    }

    public int height(AVLNode root) {
        if(root == null) return -1;
        if(root.getLeftNode() == null && root.getRightNode() == null) return 0;
        return Math.max(height(root.getLeftNode()), height(root.getRightNode())) + 1;
    }

    public void print() {
        Stack<AVLNode> stack = new Stack<>();
        ArrayList<Integer> values = new ArrayList<>();
        if (root == null) {
            return;
        }
        stack.add(root);
        while(!stack.isEmpty()) {
            AVLNode node = stack.pop();
            values.add(node.getValue());
            if (node.getRightNode() != null) {
                stack.add(node.getRightNode());
            }
            if (node.getLeftNode() != null) {
                stack.add(node.getLeftNode());
            }
        }
        System.out.println(values.toString());
    }

}
