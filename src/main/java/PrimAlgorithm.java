package main.java;

import java.util.*;

public class PrimAlgorithm {
    
    public static class PrimResult {
        public List<Graph.Edge> mstEdges;
        public int totalCost;
        public int operationsCount;
        public double executionTimeMs;
        
        public PrimResult() {
            this.mstEdges = new ArrayList<>();
            this.operationsCount = 0;
        }
    }
    
    public static PrimResult findMST(Graph graph) {
        PrimResult result = new PrimResult();
        long startTime = System.nanoTime();
        
        if (graph.nodes.isEmpty()) {
            result.executionTimeMs = (System.nanoTime() - startTime) / 1_000_000.0;
            return result;
        }
        
        PriorityQueue<Graph.Edge> pq = new PriorityQueue<>();
        Set<String> inMST = new HashSet<>();
        Map<String, List<Graph.Edge>> adjList = buildAdjList(graph);
        
        String startNode = graph.nodes.get(0);
        inMST.add(startNode);
        result.operationsCount++;
        
        for (Graph.Edge edge : adjList.get(startNode)) {
            pq.add(edge);
            result.operationsCount++;
        }
        
        while (!pq.isEmpty() && result.mstEdges.size() < graph.nodes.size() - 1) {
            Graph.Edge edge = pq.poll();
            result.operationsCount++;
            
            String nextNode = null;
            if (inMST.contains(edge.from) && !inMST.contains(edge.to)) {
                nextNode = edge.to;
            } else if (inMST.contains(edge.to) && !inMST.contains(edge.from)) {
                nextNode = edge.from;
            }
            
            if (nextNode != null) {
                result.mstEdges.add(edge);
                result.totalCost += edge.weight;
                inMST.add(nextNode);
                result.operationsCount += 2;
                
                for (Graph.Edge e : adjList.get(nextNode)) {
                    if (!inMST.contains(e.from) || !inMST.contains(e.to)) {
                        pq.add(e);
                        result.operationsCount++;
                    }
                }
            }
        }
        
        result.executionTimeMs = (System.nanoTime() - startTime) / 1_000_000.0;
        return result;
    }
    
    private static Map<String, List<Graph.Edge>> buildAdjList(Graph graph) {
        Map<String, List<Graph.Edge>> adjList = new HashMap<>();
        
        for (String node : graph.nodes) {
            adjList.put(node, new ArrayList<>());
        }
        
        for (Graph.Edge edge : graph.edges) {
            adjList.get(edge.from).add(edge);
            adjList.get(edge.to).add(new Graph.Edge(edge.to, edge.from, edge.weight));
        }
        
        return adjList;
    }
}