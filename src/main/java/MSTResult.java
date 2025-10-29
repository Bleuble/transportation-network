package main.java;
import java.util.List;
import java.util.ArrayList;

public class MSTResult {
    public String algorithm;
    public List<Graph.Edge> mstEdges;
    public int totalCost;
    public double executionTimeMs;
    
    public MSTResult(String algorithm) {
        this.algorithm = algorithm;
        this.mstEdges = new ArrayList<>();
        this.totalCost = 0;
        this.executionTimeMs = 0.0;
    }
}