package main.java;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Transportation Network Optimization ===");
        
        String inputFile = "input.json";
        String outputFile = "output.json";
        
        List<Graph> graphs = JSONParser.parseInput(inputFile);
        List<MSTResult> results = new ArrayList<>();
        
        for (Graph graph : graphs) {
            System.out.println("\n=== Graph " + graph.id + " ===");
            System.out.println("Nodes: " + graph.nodes);
            System.out.println("Edges: " + graph.edges);
            
            PrimAlgorithm.PrimResult primResult = PrimAlgorithm.findMST(graph);
            KruskalAlgorithm.KruskalResult kruskalResult = KruskalAlgorithm.findMST(graph);
            
            MSTResult comparison = new MSTResult(
                graph.id, 
                graph.getVerticesCount(), 
                graph.edges.size()
            );
            
            comparison.primTotalCost = primResult.totalCost;
            comparison.kruskalTotalCost = kruskalResult.totalCost;
            comparison.primOperations = primResult.operationsCount;
            comparison.kruskalOperations = kruskalResult.operationsCount;
            comparison.primTimeMs = primResult.executionTimeMs;
            comparison.kruskalTimeMs = kruskalResult.executionTimeMs;
            comparison.primMstEdges = primResult.mstEdges;
            comparison.kruskalMstEdges = kruskalResult.mstEdges;
            
            results.add(comparison);
            
            System.out.println("Prim    - Cost: " + primResult.totalCost + 
                             ", Time: " + String.format("%.2f", primResult.executionTimeMs) + "ms" +
                             ", Operations: " + primResult.operationsCount);
            System.out.println("Kruskal - Cost: " + kruskalResult.totalCost + 
                             ", Time: " + String.format("%.2f", kruskalResult.executionTimeMs) + "ms" +
                             ", Operations: " + kruskalResult.operationsCount);
            
            if (primResult.totalCost == kruskalResult.totalCost) {
                System.out.println("✓ Costs match - both algorithms correct!");
            }
        }
        
        JSONParser.writeOutput(outputFile, results);
        System.out.println("\n=== Analysis completed ===");
    }
}