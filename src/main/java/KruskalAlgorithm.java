package main.java;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class KruskalAlgorithm {
    public static MSTResult findMST(Graph graph) {
        MSTResult result = new MSTResult("Kruskal");
        long startTime = System.nanoTime();
        
        List<Graph.Edge> sortedEdges = new ArrayList<>(graph.edges);
        Collections.sort(sortedEdges);
        
        UnionFind uf = new UnionFind(graph.nodes);
        
        for (Graph.Edge edge : sortedEdges) {
            if (uf.union(edge.from, edge.to)) {
                result.mstEdges.add(edge);
                result.totalCost += edge.weight;
                
                if (result.mstEdges.size() == graph.nodes.size() - 1) {
                    break;
                }
            }
        }
        
        long endTime = System.nanoTime();
        result.executionTimeMs = (endTime - startTime) / 1_000_000.0;
        
        return result;
    }
}