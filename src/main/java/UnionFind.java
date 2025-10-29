import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFind {
    private Map<String, String> parent;
    private Map<String, Integer> rank;
    private int operationsCount;
    
    public UnionFind(List<String> nodes) {
        parent = new HashMap<>();
        rank = new HashMap<>();
        operationsCount = 0;
        
        for (String node : nodes) {
            parent.put(node, node);
            rank.put(node, 0);
        }
    }
    
    public String find(String x) {
        operationsCount++;
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent.get(x)));
            operationsCount++;
        }
        return parent.get(x);
    }
    
    public boolean union(String x, String y) {
        String rootX = find(x);
        String rootY = find(y);
        
        if (rootX.equals(rootY)) {
            return false;
        }
        
        // Union by rank
        if (rank.get(rootX) < rank.get(rootY)) {
            parent.put(rootX, rootY);
        } else if (rank.get(rootX) > rank.get(rootY)) {
            parent.put(rootY, rootX);
        } else {
            parent.put(rootY, rootX);
            rank.put(rootX, rank.get(rootX) + 1);
        }
        operationsCount += 2;
        
        return true;
    }
    
    public int getOperationsCount() {
        return operationsCount;
    }
}
