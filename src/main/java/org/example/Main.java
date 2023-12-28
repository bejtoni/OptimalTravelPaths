package org.example;

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        loadPlaces();
        loadConstraints();

        Graph graph1 = createGraph("src/main/resources/simple.txt");
        Graph graph2 = createGraph("src/main/resources/five_places.txt");
        Graph graph3 = createGraph("src/main/resources/ten_places.txt");
        Graph graph4 = createGraph("src/main/resources/all_places_a.txt");
        Graph graph5 = createGraph("src/main/resources/all_places_b.txt");
        Graph graph6 = createGraph("src/main/resources/complex.txt");

        Dijkstra.findShortestPaths(graph1, "Simple.txt");
        Dijkstra.findShortestPaths(graph2, "Five places.txt");
        Dijkstra.findShortestPaths(graph3, "Ten places.txt");
        Dijkstra.findShortestPaths(graph4, "All places A.txt");
        Dijkstra.findShortestPaths(graph5, "All places B.txt");
        Dijkstra.findShortestPaths(graph6, "Complex.txt");
    }


    private static Graph createGraph(String filePath) {
        Graph graph = new Graph();
        File input = new File(filePath);
        try{
            Scanner s = new Scanner(input);

            while (s.hasNextLine()){
                String currentLine = s.nextLine();
                String[] lineParts = currentLine.split(" ");

                String startShortcode = lineParts[0].trim();
                String endShortCode = lineParts[1].trim();
                Integer weight = Integer.parseInt(lineParts[2].trim());

                graph.addEdge(startShortcode, endShortCode, weight);
            }
            s.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return graph;
    }

    private static void loadConstraints() {
        File input = new File("src/main/resources/constraints.txt");

        try {
            Scanner s = new Scanner(input);
            if(s.hasNextLine())s.nextLine();

            while (s.hasNextLine()){
                String currentLine = s.nextLine();
                String[] lineParts = currentLine.split(",");

                String placeA = lineParts[0].trim();
                String placeB = lineParts[1].trim();
                String constraintType = lineParts[2].trim();
                Double probability = Double.parseDouble(lineParts[3].trim());

                Constraints constraint = new Constraints(placeA, placeB, constraintType, probability);
            }
            s.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void loadPlaces() {
        File input = new File("src/main/resources/places.txt");

        try {
            Scanner s = new Scanner(input);
            if(s.hasNextLine())s.nextLine();

            while (s.hasNextLine()){
                String currentLine = s.nextLine();
                String[] lineParts = currentLine.split(",");

                String shortcode = lineParts[0].trim();
                String name = lineParts[1].trim();

                Places.add(shortcode, name);
            }
            s.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}