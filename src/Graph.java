import java.util.*;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {
    private static class Node{
        private final String label;
        private final LinkedList<Node> connections = new LinkedList<>();

        public Node(String label) {
            this.label = label;
        }
        public String getLabel() {
            return label;
        }
        public LinkedList<Node> getConnections() {
            return connections;
        }
    }

    private final ArrayList<Node> matrix = new ArrayList<>();

    public void addNode(String label) {
        if(label == null) throw new IllegalArgumentException();
        for(Node each : matrix) {
            if(each.getLabel().equals(label)) {
                System.out.println("Node already exists");
                return;
            }
        }
        matrix.add(new Node(label));
    }

    public void removeNode(String label) {
        //implementation is wrong, as it does not delete the node passed to the connections after "addEdge";
        if(label == null) throw new IllegalArgumentException();
        for(Node each : matrix) {
            if(each.getLabel().equals(label)) {
                matrix.remove(each);
                return;
            }
        }
        System.out.println("Node does not exist.");
    }

    public void addEdge(String from, String to) {
        if(from == null || to == null) throw new IllegalArgumentException();
        else if(matrix.isEmpty()) System.out.println("Matrix is empty");
        else if(matrix.size() == 1) System.out.println("Only one Node was found");
        boolean fromNode = false;
        boolean toNode = false;
        int fromIndex = 0;
        int toIndex = 0;
        for(Node each : matrix) {
            if(each.getLabel().equals(from)) {
                fromNode = true;
                fromIndex = matrix.indexOf(each);
            }
            else if(each.getLabel().equals(to)) {
                toNode = true;
                toIndex = matrix.indexOf(each);
            }
        }

        if(fromNode && toNode) {
            matrix.get(fromIndex).getConnections().add(matrix.get(toIndex));
        }
        else System.out.println("At least one of the nodes do not exist");
    }

    public void removeEdge(String from, String to) {
        if(from == null || to == null) throw new IllegalArgumentException();
        boolean fromNode = false;
        boolean toNode = false;
        int fromIndex = 0;
        int toIndex = 0;
        for(Node each : matrix) {
            if(each.getLabel().equals(from)) {
                fromNode = true;
                fromIndex = matrix.indexOf(each);
            }
            else if(each.getLabel().equals(to)) {
                toNode = true;
                toIndex = matrix.indexOf(each);
            }
        }

        if(fromNode && toNode) {
            matrix.get(fromIndex).getConnections().remove(matrix.get(toIndex));
        }
        else System.out.println("At least one of the nodes do not exist");
    }

    public void print() {
        for(Node each : matrix) {
            System.out.println(
                    each.getLabel()
                    + " is connected with "
                    + (each.getConnections().isEmpty()? "[]" : printConnections(each)));
        }
    }

    private String printConnections(Node node) {
        StringBuilder result = new StringBuilder("[");
        for(Node each : node.getConnections()) {
            result.append(each.getLabel());
            result.append(", ");
        }
        return result.substring(0, result.length() - 2) + "]";
    }

    private void traverseDepthFirst(String label, Set<Node> set) {
        for(Node each : matrix) {
            if(each.getLabel().equals(label)) {
                if(set.add(each)) System.out.println(each.getLabel());
                for(Node item : each.getConnections()) {
                    traverseDepthFirst(item.getLabel(), set);
                }
            }
        }
    }

    public void iterateDepthFirst(String label) {
        iterateDepthFirst(label, new HashSet<>());
    }

    private void iterateDepthFirst(String label, Set<Node> set) {
        Stack<Node> stack = new Stack<>();
        for(Node each : matrix) {
            if(each.getLabel().equals(label)) {
                stack.push(each);
                while(!stack.isEmpty()) {
                    Node current = stack.pop();

                    if(set.contains(current)) continue;

                    else System.out.println(current.getLabel());

                    set.add(current);

                    for(Node item : current.getConnections()) {
                        if(!set.contains(item))
                        stack.push(item);
                    }
                }
            }
        }
    }

    public void printTraversalDepth(String label) {
        traverseDepthFirst(label, new HashSet<>());
    }

    public void traverseBreadthFirst(String label) {
        traverseBreadthFirst(label, new HashSet<>());
    }

    private void traverseBreadthFirst(String label, Set<Node> set) {
        Queue<Node> queue = new ArrayDeque<>();
        for(Node each : matrix) {
            if(each.getLabel().equals(label)) {
                queue.add(each);
                while(!queue.isEmpty()) {
                    Node current = queue.remove();

                    if(set.contains(current)) continue;

                    else System.out.println(current.getLabel());

                    set.add(current);

                    for(Node item : current.getConnections()) {
                        if(!set.contains(item))
                            queue.add(item);
                    }
                }
            }
        }
    }



}
