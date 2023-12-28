package org.example;

public class NodeDistance {
    private final Node start;
    private final Node end;
    private final Integer weight;

    public NodeDistance(Node start, Node end, Integer weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public Integer getWeight() {
        return weight;
    }

}
