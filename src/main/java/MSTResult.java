package main.java;

import java.util.ArrayList;
import java.util.List;

public class MSTResult {
    public int graphId;
    public int vertices;
    public int edges;
    public int primTotalCost;
    public int kruskalTotalCost;
    public int primOperations;
    public int kruskalOperations;
    public double primTimeMs;
    public double kruskalTimeMs;
    public List<Graph.Edge> primMstEdges;
    public List<Graph.Edge> kruskalMstEdges;
    
    public MSTResult(int graphId, int vertices, int edges) {
        this.graphId = graphId;
        this.vertices = vertices;
        this.edges = edges;
        this.primMstEdges = new ArrayList<>();
        this.kruskalMstEdges = new ArrayList<>();
    }
}