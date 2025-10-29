import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Тест алгоритма Краскала ===");
        
        // Создаем тестовый граф вручную
        Graph graph = createTestGraph();
        
        // Запускаем алгоритм Краскала
        MSTResult.AlgorithmResult kruskalResult = KruskalAlgorithm.findMST(graph);
        
        // Выводим результаты
        System.out.println("Граф " + graph.id + ":");
        System.out.println("Вершины: " + graph.nodes);
        System.out.println("Рёбра MST: " + kruskalResult.mstEdges);
        System.out.println("Общая стоимость: " + kruskalResult.totalCost);
        System.out.println("Операций: " + kruskalResult.operationsCount);
        System.out.println("Время: " + kruskalResult.executionTimeMs + " ms");
        
        // Тестируем алгоритм Прима (временный)
        MSTResult.AlgorithmResult primResult = PrimAlgorithm.findMST(graph);
        System.out.println("\n=== Тест алгоритма Прима ===");
        System.out.println("Рёбра MST: " + primResult.mstEdges);
        System.out.println("Общая стоимость: " + primResult.totalCost);
        System.out.println("Операций: " + primResult.operationsCount);
        System.out.println("Время: " + primResult.executionTimeMs + " ms");
    }
    
    private static Graph createTestGraph() {
        Graph graph = new Graph();
        graph.id = 1;
        graph.nodes = Arrays.asList("A", "B", "C", "D");
        graph.edges = Arrays.asList(
            new Graph.Edge("A", "B", 1),
            new Graph.Edge("B", "C", 2),
            new Graph.Edge("C", "D", 3),
            new Graph.Edge("A", "D", 4),
            new Graph.Edge("B", "D", 5)
        );
        return graph;
    }
}
