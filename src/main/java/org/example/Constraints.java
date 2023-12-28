package org.example;

import java.util.ArrayList;

public class Constraints {
    private final String startShortcode;
    private final String endShortcode;
    private final String constraintName;
    private final Double probability;

    private static final ArrayList<Constraints> allConstraints = new ArrayList<>();


    public Constraints(String startShortcode, String endShortcode, String constraintName, Double probability) {
        this.startShortcode = startShortcode;
        this.endShortcode = endShortcode;
        this.constraintName = constraintName;
        this.probability = probability;

        allConstraints.add(this);
    }

    public static ArrayList<Constraints> getAllConstraints() {
        return allConstraints;
    }

    public String getStartShortcode() {
        return startShortcode;
    }

    public String getEndShortcode() {
        return endShortcode;
    }

    public String getConstraintName() {
        return constraintName;
    }

    public double getProbability() {
        return probability;
    }
}


