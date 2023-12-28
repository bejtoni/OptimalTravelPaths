package org.example;

import java.util.*;

public class Dijkstra {
    public static void findShortestPaths(Graph graph) {
        applyConstraints(graph, Constraints.getAllConstraints());

        Map<String, Node> nodes = graph.getNodesInTheGraphMap();

        for (Node startNode : nodes.values()) {
            Map<Node, Integer> distance = new HashMap<>();
            Map<Node, Node> previous = new HashMap<>();
            PriorityQueue<NodeDistance> pq = new PriorityQueue<>(Comparator.comparingInt(NodeDistance::getWeight));

            distance.put(startNode, 0);
            pq.add(new NodeDistance(null, startNode, 0));

            while (!pq.isEmpty()) {
                NodeDistance current = pq.poll();
                Node currentNode = current.getEnd();

                for (Map.Entry<Node, Integer> entry : currentNode.getNodeNeighboursMap().entrySet()) {
                    Node neighbour = entry.getKey();
                    int newDist = distance.get(currentNode) + entry.getValue();

                    if (newDist >= 0 && (distance.get(neighbour) == null || newDist < distance.get(neighbour))) {
                        distance.put(neighbour, newDist);
                        previous.put(neighbour, currentNode);
                        pq.add(new NodeDistance(currentNode, neighbour, newDist));
                    }
                }
            }

            for (Node node : nodes.values()) {
                if (!node.equals(startNode)) {
                    Node target = node;
                    String startFullName = startNode.getFullName();
                    String targetFullName = target.getFullName();
                    int optimalTime = (distance.containsKey(target) && distance.get(target) >= 0) ? distance.get(target) : -1;

                    System.out.println(
                            (startFullName != null ? startFullName : startNode.getShortName()) +
                                    " -> " +
                                    (targetFullName != null ? targetFullName : target.getShortName()) +
                                    ": " + optimalTime
                    );
                }
            }
        }
    }

    public static void findShortestPath(Graph graph, String startShortcode, String endShortcode) {
        Map<String, Node> nodes = graph.getNodesInTheGraphMap();

        Node startNode = nodes.get(startShortcode.toUpperCase());
        Node endNode = nodes.get(endShortcode.toUpperCase());

        if (startNode == null || endNode == null) {
            System.out.println("Invalid start or end node.");
            return;
        }

        Map<Node, Integer> distance = new HashMap<>();
        Map<Node, Node> previous = new HashMap<>();
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>(Comparator.comparingInt(NodeDistance::getWeight));

        distance.put(startNode, 0);
        pq.add(new NodeDistance(null, startNode, 0));

        while (!pq.isEmpty()) {
            NodeDistance current = pq.poll();
            Node currentNode = current.getEnd();

            for (Map.Entry<Node, Integer> entry : currentNode.getNodeNeighboursMap().entrySet()) {
                Node neighbour = entry.getKey();
                int newDist = distance.get(currentNode) + entry.getValue();

                if (newDist >= 0 && (distance.get(neighbour) == null || newDist < distance.get(neighbour))) {
                    distance.put(neighbour, newDist);
                    previous.put(neighbour, currentNode);
                    pq.add(new NodeDistance(currentNode, neighbour, newDist));
                }
            }
        }

        int optimalTime = (distance.containsKey(endNode) && distance.get(endNode) >= 0) ? distance.get(endNode) : -1;

        String startFullName = Places.getAllPlacesMap().get(startShortcode);
        String endFullName = Places.getAllPlacesMap().get(endShortcode);

        System.out.println(startFullName + " -> " + endFullName + ": " + optimalTime);

        /*if (optimalTime >= 0) {
            System.out.println("Optimal Path:");
            printOptimalPath(previous, startNode, endNode);
        }*/
    }

    private static void printOptimalPath(Map<Node, Node> previous, Node startNode, Node endNode) {
        List<Node> path = new ArrayList<>();
        Node currentNode = endNode;

        while (currentNode != null) {
            path.add(currentNode);
            currentNode = previous.get(currentNode);
        }

        Collections.reverse(path);

        for (Node node : path) {
            System.out.print(node.getFullName() + " ");
        }
        System.out.println();
    }

    public static void applyConstraints(Graph graph, List<Constraints> constraints) {
        double globalRandomValue = Math.random(); // Generate a single random value

        for (Constraints constraint : constraints) {
            String startShortcode = constraint.getStartShortcode();
            String endShortcode = constraint.getEndShortcode();

            // Check if the nodes for constraints exist in the graph
            Node startNode = graph.getNodesInTheGraphMap().get(startShortcode);
            Node endNode = graph.getNodesInTheGraphMap().get(endShortcode);

            if (startNode != null && endNode != null) {
                if (globalRandomValue > constraint.getProbability()) {
                    System.out.println("Constraint applied between " +
                            startShortcode + " and " + endShortcode +
                            " with probability " + constraint.getProbability() + " -> " + globalRandomValue);
                    graph.disableEdge(startShortcode, endShortcode);
                }
            }
        }
    }
}