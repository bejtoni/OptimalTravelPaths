package org.example;

import java.util.*;

public class Constraints {
    private final String startShortcode;
    private final String endShortcode;
    private final String constraintType;
    private final Double probability;

    private static List<Constraints> allConstraints = new ArrayList<>();


    public Constraints(String startShortcode, String endShortcode, String constraintName, Double probability) {
        this.startShortcode = startShortcode;
        this.endShortcode = endShortcode;
        this.constraintType = constraintName;
        this.probability = probability;

        allConstraints.add(this);
    }

    public static List<Constraints> getAllConstraints() {
        return allConstraints;
    }

    public String getStartShortcode() {
        return startShortcode;
    }

    public String getEndShortcode() {
        return endShortcode;
    }

    public String getConstraintType() {
        return constraintType;
    }

    public double getProbability() {
        return probability;
    }
}


