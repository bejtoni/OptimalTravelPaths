import junit.framework.TestCase;

import org.example.*;
import java.util.*;

public class DijkstraTest extends TestCase {

    public void testIfThereIsAPathToDestinationNode() {
        Main.loadPlaces();
        List<Constraints> allConstraints = new ArrayList<>();

        Graph graph = new Graph();
        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "C", 15);
        graph.addEdge("E", "D", 20);

        assertEquals(-1, Dijkstra.findShortestPath(graph, "A", "D", allConstraints));
        assertEquals(10, Dijkstra.findShortestPath(graph, "A", "B", allConstraints));
        assertEquals(15, Dijkstra.findShortestPath(graph, "B", "C", allConstraints));
    }

    public void testIfAllNodesAreConnected(){
        Main.loadPlaces();
        List<Constraints> allConstraints = new ArrayList<>();
        

        Graph graph = new Graph();
        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "C", 15);
        graph.addEdge("E", "D", 20);
        graph.addEdge("A", "D", 20);
        graph.addEdge("E", "A", 20);
        graph.addEdge("B", "E", 20);
        graph.addEdge("C", "D", 20);
        graph.addEdge("D", "E", 20);


        assertEquals(10, Dijkstra.findShortestPath(graph, "A", "B", allConstraints));
        assertEquals(25, Dijkstra.findShortestPath(graph, "A", "C", allConstraints));
        assertEquals(20, Dijkstra.findShortestPath(graph, "A", "D", allConstraints));
        assertEquals(30, Dijkstra.findShortestPath(graph, "A", "E", allConstraints));
        assertEquals(40, Dijkstra.findShortestPath(graph, "B", "A", allConstraints));
        assertEquals(15, Dijkstra.findShortestPath(graph, "B", "C", allConstraints));
        assertEquals(35, Dijkstra.findShortestPath(graph, "B", "D", allConstraints));
        assertEquals(20, Dijkstra.findShortestPath(graph, "B", "E", allConstraints));
        assertEquals(60, Dijkstra.findShortestPath(graph, "C", "A", allConstraints));
        assertEquals(70, Dijkstra.findShortestPath(graph, "C", "B", allConstraints));
        assertEquals(20, Dijkstra.findShortestPath(graph, "C", "D", allConstraints));
        assertEquals(40, Dijkstra.findShortestPath(graph, "C", "E", allConstraints));
        assertEquals(40, Dijkstra.findShortestPath(graph, "D", "A", allConstraints));
        assertEquals(50, Dijkstra.findShortestPath(graph, "D", "B", allConstraints));
        assertEquals(65, Dijkstra.findShortestPath(graph, "D", "C", allConstraints));
        assertEquals(20, Dijkstra.findShortestPath(graph, "D", "E", allConstraints));
        assertEquals(20, Dijkstra.findShortestPath(graph, "E", "A", allConstraints));
        assertEquals(30, Dijkstra.findShortestPath(graph, "E", "B", allConstraints));
        assertEquals(45, Dijkstra.findShortestPath(graph, "E", "C", allConstraints));
        assertEquals(20, Dijkstra.findShortestPath(graph, "E", "D", allConstraints));
    }

    public void testInvalidDestinationNode() {
        Main.loadPlaces();
        List<Constraints> allConstraints = new ArrayList<>();

        Graph graph = new Graph();
        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "C", 15);
        graph.addEdge("E", "D", 20);

        assertEquals(-1, Dijkstra.findShortestPath(graph, "A", "F", allConstraints));
    }

    public void testWithNegativeTime() {
        Main.loadPlaces();
        List<Constraints> allConstraints = new ArrayList<>();

        Graph graph = new Graph();
        graph.addEdge("A", "B", -10);
        graph.addEdge("B", "C", 15);
        graph.addEdge("C", "D", 20);

        assertEquals(-1, Dijkstra.findShortestPath(graph, "A", "D", allConstraints));
    }

    public void testGraphWithSelfLoop() {
        Main.loadPlaces();
        List<Constraints> allConstraints = new ArrayList<>();

        Graph graph = new Graph();
        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "C", 15);
        graph.addEdge("C", "D", 20);
        graph.addEdge("D", "D", 0);

        assertEquals(45, Dijkstra.findShortestPath(graph, "A", "D",allConstraints));
        assertEquals(0, Dijkstra.findShortestPath(graph, "D", "D", allConstraints));
    }

    public void testWithCertainProbability() {
        Main.loadPlaces();
        List<Constraints> allConstraints = new ArrayList<>();
        allConstraints.add(new Constraints("A", "B", "x", 1.0));
        allConstraints.add(new Constraints("B", "D", "x", 1.0));
        allConstraints.add(new Constraints("B", "M", "x", 1.0));
        allConstraints.add(new Constraints("C", "I", "x", 1.0));
        allConstraints.add(new Constraints("G", "I", "x", 1.0));

        Graph graph = new Graph();
        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "C", 15);
        graph.addEdge("B", "D", 20);
        graph.addEdge("C", "I", 20);

        assertEquals(-1, Dijkstra.findShortestPath(graph, "A", "B", allConstraints));
        assertEquals(15, Dijkstra.findShortestPath(graph, "B", "C", allConstraints));
        assertEquals(-1, Dijkstra.findShortestPath(graph, "B", "D", allConstraints));
        assertEquals(-1, Dijkstra.findShortestPath(graph, "C", "I", allConstraints));
        assertEquals(-1, Dijkstra.findShortestPath(graph, "A", "C", allConstraints));
    }

    public void testWithNoConstraints() {
        Main.loadPlaces();
        List<Constraints> allConstraints = new ArrayList<>();
        allConstraints.add(new Constraints("A", "B", "x",0.0));
        allConstraints.add(new Constraints("B", "D", "x", 0.0));
        allConstraints.add(new Constraints("B", "M", "x", 0.0));

        Graph graph = new Graph();
        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "C", 15);
        graph.addEdge("B", "D", 20);
        graph.addEdge("X", "Y", 20);

        assertEquals(10, Dijkstra.findShortestPath(graph, "A", "B", allConstraints));
        assertEquals(15, Dijkstra.findShortestPath(graph, "B", "C", allConstraints));
        assertEquals(20, Dijkstra.findShortestPath(graph, "B", "D", allConstraints));
        assertEquals(20, Dijkstra.findShortestPath(graph, "X", "Y", allConstraints));
    }

}