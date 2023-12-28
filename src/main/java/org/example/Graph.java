package org.example;

import java.util.HashMap;
import java.util.Map;

public class Graph {
    private Map<String, Node> nodesInTheGraphMap;
    public Graph(){
        this.nodesInTheGraphMap = new HashMap<>();
    }

    public void addLine(String startShortcode, String endShortCode, Integer weight) {
        Node start = nodesInTheGraphMap.computeIfAbsent(startShortcode, Node::new);
        Node end = nodesInTheGraphMap.computeIfAbsent(endShortCode, Node::new);

        start.addNeighbour(end, weight);
        nodesInTheGraphMap.put(startShortcode, start);
        nodesInTheGraphMap.put(endShortCode, end);
    }


    public Map<String, Node> getNodesInTheGraphMap() {
        return nodesInTheGraphMap;
    }

    public void printNodes() {
        System.out.println("Nodes in the Graph:");
        for (Map.Entry<String, Node> entry : nodesInTheGraphMap.entrySet()) {
            String nodeShortcode = entry.getKey();
            Node node = entry.getValue();
            System.out.println("Node Shortcode: " + nodeShortcode + ", Node Name: " + node.getName());
        }
    }
}
