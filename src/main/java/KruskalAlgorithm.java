package main.java;

import java.util.*;

public class KruskalAlgorithm {
    
    public static class KruskalResult {
        public List<Graph.Edge> mstEdges;
        public int totalCost;
        public int operationsCount;
        public double executionTimeMs;
        
        public KruskalResult() {
            this.mstEdges = new ArrayList<>();
            this.operationsCount = 0;
        }
    }
    
    public static KruskalResult findMST(Graph graph) {
        KruskalResult result = new KruskalResult();
        long startTime = System.nanoTime();
        
        List<Graph.Edge> sortedEdges = new ArrayList<>(graph.edges);
        Collections.sort(sortedEdges);
        result.operationsCount += graph.edges.size();
        
        UnionFind uf = new UnionFind(graph.nodes);
        
        for (Graph.Edge edge : sortedEdges) {
            result.operationsCount++;
            if (uf.union(edge.from, edge.to)) {
                result.mstEdges.add(edge);
                result.totalCost += edge.weight;
                result.operationsCount++;
                
                if (result.mstEdges.size() == graph.nodes.size() - 1) {
                    break;
                }
            }
        }
        
        result.executionTimeMs = (System.nanoTime() - startTime) / 1_000_000.0;
        return result;
    }
}