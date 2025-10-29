package main.java;

import java.util.*;

public class Graph {
    public int id;
    public List<String> nodes;
    public List<Edge> edges;
    
    public Graph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
    
    public Graph(int id) {
        this();
        this.id = id;
    }
    
    public void addNode(String node) {
        if (!nodes.contains(node)) {
            nodes.add(node);
        }
    }
    
    public void addEdge(String from, String to, int weight) {
        addNode(from);
        addNode(to);
        edges.add(new Edge(from, to, weight));
    }
    
    public int getVerticesCount() {
        return nodes.size();
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
            return "{\"from\": \"" + from + "\", \"to\": \"" + to + "\", \"weight\": " + weight + "}";
        }
    }
}