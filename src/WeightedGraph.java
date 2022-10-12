import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraph {
    private static class Node {
        private final String label;
        private final List<Edge> edges = new ArrayList<>();
        public Node(String label) {
            this.label = label;
        }
        public String getLabel() {
            return label;
        }
        @Override
        public String toString() {
            return getLabel();
        }
        public void addEdge(Node to, int weight) {
            edges.add(new Edge(this, to, weight));
        }
    }
    private static class Edge {
        private final Node from;
        private final Node to;
        private final int weight;
        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public String toString() {
            return from + " -> " + to;
        }
    }

    private final Map<String, Node> matrix = new HashMap<>();

    public void addNode(String label) {
//        if(label == null) System.out.println("Label cannot be null");
        matrix.putIfAbsent(label, new Node(label));
    }

    public void addEdge(String from, String to, int weight) {
        if(from == null || to == null || weight < 0)
            System.out.println("Null values cannot be passed");

        else if(!matrix.containsKey(from) || !matrix.containsKey(to))
            System.out.println("One of the keys passed do not exist");

        Node fromNode = matrix.get(from);
        Node toNode = matrix.get(to);

        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

    public void print() {
        for(var item : matrix.values()) {
            var targets = item.edges;
            if(!targets.isEmpty()) {
                System.out.println(item + " is connected to " + targets);
            }
        }
    }


}
