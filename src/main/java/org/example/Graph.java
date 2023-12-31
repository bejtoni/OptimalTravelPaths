package org.example;

import java.util.*;

public class Graph {
    private final Map<String, Node> nodesInTheGraphMap;
    public Graph(){
        this.nodesInTheGraphMap = new HashMap<>();
    }

    public void addEdge(String startShortcode, String endShortCode, Integer weight) {
        //Provjeri ima li key sa prvi arg, ako nema kreira novi obj tipa Node sa tim key
        Node start = nodesInTheGraphMap.computeIfAbsent(startShortcode, Node::new);
        Node end = nodesInTheGraphMap.computeIfAbsent(endShortCode, Node::new);

        start.addNeighbour(end, weight);
    }

    public void disableEdge(String startShortcode, String endShortcode) {
        Node start = nodesInTheGraphMap.get(startShortcode);
        Node end = nodesInTheGraphMap.get(endShortcode);

        start.disableNeighbour(end);
    }

    public Map<String, Node> getNodesInTheGraphMap() {
        return nodesInTheGraphMap;
    }

    public boolean edgeExists(String startShortcode, String endShortcode) {
        Node startNode = nodesInTheGraphMap.get(startShortcode);
        Node endNode = nodesInTheGraphMap.get(endShortcode);

        return startNode != null && endNode != null && startNode.getNodeNeighboursMap().containsKey(endNode);
    }

    /*public void printNodes() {
        System.out.println("Nodes in the Graph:");
        for (Map.Entry<String, Node> entry : nodesInTheGraphMap.entrySet()) {
            String nodeShortcode = entry.getKey();
            Node node = entry.getValue();
            System.out.println("Node Shortcode: " + nodeShortcode + ", Node Name: " + node.getFullName());
        }
    }*/
}
