import java.util.List;
import java.util.ArrayList;

public class MSTResult {
    public int graphId;
    public InputStats inputStats;
    public AlgorithmResult prim;
    public AlgorithmResult kruskal;
    
    public MSTResult(int graphId) {
        this.graphId = graphId;
        this.inputStats = new InputStats();
        this.prim = new AlgorithmResult("prim");
        this.kruskal = new AlgorithmResult("kruskal");
    }
    
    public static class InputStats {
        public int vertices;
        public int edges;
    }
    
    public static class AlgorithmResult {
        public String algorithm;
        public List<Graph.Edge> mstEdges;
        public int totalCost;
        public int operationsCount;
        public double executionTimeMs;
        
        public AlgorithmResult(String algorithm) {
            this.algorithm = algorithm;
            this.mstEdges = new ArrayList<>();
        }
    }
}
