import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class KruskalAlgorithm {
    
    public static MSTResult.AlgorithmResult findMST(Graph graph) {
        MSTResult.AlgorithmResult result = new MSTResult.AlgorithmResult("kruskal");
        
        long startTime = System.nanoTime();
        
        // Сортируем рёбра по весу
        List<Graph.Edge> sortedEdges = new ArrayList<>(graph.edges);
        Collections.sort(sortedEdges);
        
        UnionFind uf = new UnionFind(graph.nodes);
        result.totalCost = 0;
        
        for (Graph.Edge edge : sortedEdges) {
            if (uf.union(edge.from, edge.to)) {
                result.mstEdges.add(edge);
                result.totalCost += edge.weight;
                
                // MST содержит V-1 рёбер
                if (result.mstEdges.size() == graph.nodes.size() - 1) {
                    break;
                }
            }
        }
        
        long endTime = System.nanoTime();
        result.executionTimeMs = (endTime - startTime) / 1_000_000.0;
        result.operationsCount = uf.getOperationsCount();
        
        return result;
    }
}
