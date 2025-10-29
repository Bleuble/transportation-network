package main.java;

import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class JSONParser {
    
    public static List<Graph> parseInput(String filename) {
        List<Graph> graphs = new ArrayList<>();
        
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            graphs = parseGraphsFromJson(content);
        } catch (Exception e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
            graphs = createTestGraphs();
        }
        
        return graphs;
    }
    
    private static List<Graph> parseGraphsFromJson(String json) {
        List<Graph> graphs = new ArrayList<>();
        
        Pattern graphPattern = Pattern.compile("\\{\\s*\"id\"\\s*:\\s*(\\d+).*?\"nodes\"\\s*:\\s*\\[(.*?)\\].*?\"edges\"\\s*:\\s*\\[(.*?)\\]\\s*\\}", Pattern.DOTALL);
        Matcher graphMatcher = graphPattern.matcher(json);
        
        while (graphMatcher.find()) {
            int id = Integer.parseInt(graphMatcher.group(1));
            String nodesStr = graphMatcher.group(2);
            String edgesStr = graphMatcher.group(3);
            
            Graph graph = new Graph(id);
            parseNodes(graph, nodesStr);
            parseEdges(graph, edgesStr);
            graphs.add(graph);
        }
        
        return graphs;
    }
    
    private static void parseNodes(Graph graph, String nodesStr) {
        Pattern nodePattern = Pattern.compile("\"([^\"]+)\"");
        Matcher nodeMatcher = nodePattern.matcher(nodesStr);
        
        while (nodeMatcher.find()) {
            graph.addNode(nodeMatcher.group(1));
        }
    }
    
    private static void parseEdges(Graph graph, String edgesStr) {
        Pattern edgePattern = Pattern.compile("\\{\\s*\"from\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"to\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"weight\"\\s*:\\s*(\\d+)\\s*\\}");
        Matcher edgeMatcher = edgePattern.matcher(edgesStr);
        
        while (edgeMatcher.find()) {
            String from = edgeMatcher.group(1);
            String to = edgeMatcher.group(2);
            int weight = Integer.parseInt(edgeMatcher.group(3));
            graph.addEdge(from, to, weight);
        }
    }
    
    private static List<Graph> createTestGraphs() {
        List<Graph> graphs = new ArrayList<>();
        
        Graph graph1 = new Graph(1);
        graph1.addEdge("A", "B", 4);
        graph1.addEdge("A", "C", 3);
        graph1.addEdge("B", "C", 2);
        graph1.addEdge("B", "D", 5);
        graph1.addEdge("C", "D", 7);
        graph1.addEdge("C", "E", 8);
        graph1.addEdge("D", "E", 6);
        graphs.add(graph1);
        
        Graph graph2 = new Graph(2);
        graph2.addEdge("A", "B", 1);
        graph2.addEdge("A", "C", 4);
        graph2.addEdge("B", "C", 2);
        graph2.addEdge("C", "D", 3);
        graph2.addEdge("B", "D", 5);
        graphs.add(graph2);
        
        return graphs;
    }
    
    public static void writeOutput(String filename, List<MSTResult> results) {
        try {
            StringBuilder json = new StringBuilder();
            json.append("{\n");
            json.append("  \"results\": [\n");
            
            for (int i = 0; i < results.size(); i++) {
                MSTResult result = results.get(i);
                json.append("    {\n");
                json.append("      \"graph_id\": ").append(result.graphId).append(",\n");
                
                json.append("      \"input_stats\": {\n");
                json.append("        \"vertices\": ").append(result.vertices).append(",\n");
                json.append("        \"edges\": ").append(result.edges).append("\n");
                json.append("      },\n");
                
                json.append("      \"prim\": {\n");
                json.append("        \"mst_edges\": [\n");
                for (int j = 0; j < result.primMstEdges.size(); j++) {
                    Graph.Edge edge = result.primMstEdges.get(j);
                    json.append("          {\"from\": \"").append(edge.from).append("\", \"to\": \"")
                        .append(edge.to).append("\", \"weight\": ").append(edge.weight).append("}");
                    if (j < result.primMstEdges.size() - 1) json.append(",");
                    json.append("\n");
                }
                json.append("        ],\n");
                json.append("        \"total_cost\": ").append(result.primTotalCost).append(",\n");
                json.append("        \"operations_count\": ").append(result.primOperations).append(",\n");
                json.append("        \"execution_time_ms\": ").append(String.format("%.2f", result.primTimeMs)).append("\n");
                json.append("      },\n");
                
                json.append("      \"kruskal\": {\n");
                json.append("        \"mst_edges\": [\n");
                for (int j = 0; j < result.kruskalMstEdges.size(); j++) {
                    Graph.Edge edge = result.kruskalMstEdges.get(j);
                    json.append("          {\"from\": \"").append(edge.from).append("\", \"to\": \"")
                        .append(edge.to).append("\", \"weight\": ").append(edge.weight).append("}");
                    if (j < result.kruskalMstEdges.size() - 1) json.append(",");
                    json.append("\n");
                }
                json.append("        ],\n");
                json.append("        \"total_cost\": ").append(result.kruskalTotalCost).append(",\n");
                json.append("        \"operations_count\": ").append(result.kruskalOperations).append(",\n");
                json.append("        \"execution_time_ms\": ").append(String.format("%.2f", result.kruskalTimeMs)).append("\n");
                json.append("      }\n");
                
                json.append("    }");
                if (i < results.size() - 1) json.append(",");
                json.append("\n");
            }
            
            json.append("  ]\n");
            json.append("}");
            
            Files.write(Paths.get(filename), json.toString().getBytes());
            System.out.println("Results written to: " + filename);
            
        } catch (Exception e) {
            System.out.println("Error writing output: " + e.getMessage());
        }
    }
}