package org.example;

import java.io.FileWriter;
import java.util.*;

public class Dijkstra {
    public static void findShortestPaths(Graph graph, String fileName) {
        List<String> appliedConstraints = applyConstraints(graph, Constraints.getAllConstraints());

        Map<String, Node> nodes = graph.getNodesInTheGraphMap();

        try {
            FileWriter fw = new FileWriter(fileName);

            if (appliedConstraints.isEmpty()) {
                fw.write("No constraints applied" + "\n\n");
            } else {
                fw.write("Applied constraints: " + "\n");
                for (String constraint : appliedConstraints) {
                    fw.write(constraint + "\n");
                }
                fw.write("\n");
            }

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

                        boolean edgeDisabled = Constraints.getAllConstraints().stream()
                                .filter(c -> c.getStartShortcode().equals(currentNode.getShortName())
                                        && c.getEndShortcode().equals(neighbour.getShortName()))
                                .anyMatch(c -> Math.random() <= c.getProbability());

                        if (!edgeDisabled && (newDist >= 0 && (distance.get(neighbour) == null || newDist < distance.get(neighbour)))) {
                            distance.put(neighbour, newDist);
                            previous.put(neighbour, currentNode);
                            pq.add(new NodeDistance(currentNode, neighbour, newDist));
                        }
                    }
                }

                for (Node node : nodes.values()) {
                    if (!node.equals(startNode)) {
                        String startFullName = startNode.getFullName();
                        String targetFullName = node.getFullName();
                        int optimalTime = (distance.containsKey(node) && distance.get(node) >= 0) ? distance.get(node) : -1;

                        fw.write(
                                (startFullName != null ? startFullName : startNode.getShortName()) +
                                        " -> " +
                                        (targetFullName != null ? targetFullName : node.getShortName()) +
                                        ": " + optimalTime + System.lineSeparator()
                        );
                    }
                }
            }

            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List<String> applyConstraints(Graph graph, List<Constraints> constraints) {
        List<String> appliedConstraints = new ArrayList<>();

        for (Constraints constraint : constraints) {
            String startShortcode = constraint.getStartShortcode();
            String endShortcode = constraint.getEndShortcode();

            Node startNode = graph.getNodesInTheGraphMap().get(startShortcode);
            Node endNode = graph.getNodesInTheGraphMap().get(endShortcode);

            if (startNode != null && endNode != null && graph.edgeExists(startShortcode, endShortcode)) {
                double globalRandomValue = Math.random();
                //double globalRandomValue = 1;

                if (globalRandomValue >= constraint.getProbability()) {
                    graph.disableEdge(startShortcode, endShortcode);
                    appliedConstraints.add(startShortcode + " " + endShortcode + " " + constraint.getConstraintType() + " " + constraint.getProbability());
                }
            }
        }

        return appliedConstraints;
    }


    public static int findShortestPath(Graph graph, String startShortcode, String endShortcode, List<Constraints> constraints) {
        Map<String, Node> nodes = graph.getNodesInTheGraphMap();
        Node startNode = nodes.get(startShortcode.toUpperCase());
        Node endNode = nodes.get(endShortcode.toUpperCase());

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

                // Check if there is a constraint disabling the edge
                boolean edgeDisabled = constraints.stream()
                        .anyMatch(c -> c.getStartShortcode().equals(currentNode.getShortName())
                                && c.getEndShortcode().equals(neighbour.getShortName())
                                && Math.random() <= c.getProbability());

                if (!edgeDisabled && (newDist >= 0 && (distance.get(neighbour) == null || newDist < distance.get(neighbour)))) {
                    distance.put(neighbour, newDist);
                    previous.put(neighbour, currentNode);
                    pq.add(new NodeDistance(currentNode, neighbour, newDist));
                }
            }
        }
        return distance.getOrDefault(endNode, -1);
    }
}