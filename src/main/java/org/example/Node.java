package org.example;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private final String name;
    private final String fullName;
    private Map<Node, Integer> nodeNeighboursMap = new HashMap<>();

    public Node(String name){
        this.name = name;
        this.fullName = getFullNameFromPlaces(name);
    }

    public void addNeighbour(Node end, Integer weight){
        nodeNeighboursMap.put(end, weight);
    }

    public void disableNeighbour(Node neighbour) {
        nodeNeighboursMap.remove(neighbour);
    }
    public Map<Node, Integer> getNodeNeighboursMap() {
        return nodeNeighboursMap;
    }

    private String getFullNameFromPlaces(String shortcode) {
        return Places.getAllPlacesMap().get(shortcode);
    }

    public String getName() {
        return fullName;
    }

    public void printNeighbours() {
        System.out.println("Neighbours of Node " + name + ":");
        for (Map.Entry<Node, Integer> entry : nodeNeighboursMap.entrySet()) {
            Node neighbour = entry.getKey();
            Integer weight = entry.getValue();
            System.out.println("Neighbour Name: " + neighbour.getName() + ", Weight: " + weight);
        }
    }
}
