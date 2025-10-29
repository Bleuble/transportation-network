package main.java;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Kruskal Algorithm Test ===");
        
        Graph graph = createTestGraph();
        
        System.out.println("Graph nodes: " + graph.nodes);
        System.out.println("Graph edges: " + graph.edges);
        
        MSTResult result = KruskalAlgorithm.findMST(graph);
        
        System.out.println("\n=== Results ===");
        System.out.println("Algorithm: " + result.algorithm);
        System.out.println("MST edges: " + result.mstEdges);
        System.out.println("Total cost: " + result.totalCost);
        System.out.println("Execution time: " + result.executionTimeMs + " ms");
        
        System.out.println("\n=== Test completed successfully! ===");
    }
    
    private static Graph createTestGraph() {
        Graph graph = new Graph();
        
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 2);
        graph.addEdge("C", "D", 3);
        graph.addEdge("A", "D", 4);
        graph.addEdge("B", "D", 5);
        
        return graph;
    }
}
