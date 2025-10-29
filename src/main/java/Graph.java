import java.util.List;
import java.util.ArrayList;

public class Graph {
    public int id;
    public List<String> nodes;
    public List<Edge> edges;
    
    public Graph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
    
    public static class Edge implements Comparable<Edge> {
        public String from;
        public String to;
        public int weight;
        
        public Edge(String from, String to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
        
        @Override
        public String toString() {
            return "{" + from + "-" + to + ": " + weight + "}";
        }
    }
}
