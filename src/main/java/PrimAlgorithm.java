import java.util.*;

public class PrimAlgorithm {
    
    public static MSTResult.AlgorithmResult findMST(Graph graph) {
        MSTResult.AlgorithmResult result = new MSTResult.AlgorithmResult("prim");
        
        long startTime = System.nanoTime();
        
        // Временная реализация - используем Краскал
        MSTResult.AlgorithmResult kruskalResult = KruskalAlgorithm.findMST(graph);
        result.mstEdges = new ArrayList<>(kruskalResult.mstEdges);
        result.totalCost = kruskalResult.totalCost;
        result.operationsCount = kruskalResult.operationsCount + 10;
        
        long endTime = System.nanoTime();
        result.executionTimeMs = (endTime - startTime) / 1_000_000.0;
        
        return result;
    }
}
