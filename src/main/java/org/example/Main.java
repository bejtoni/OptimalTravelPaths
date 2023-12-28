package org.example;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Popuni mjesta shodno pocetnom slovu i full names
        fillAllPlaces();

        Graph graph1 = createGraph("src/main/resources/ten_places.txt");


        Dijkstra.findShortestPaths(graph1);
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

                graph.addLine(startShortcode, endShortCode, weight);
            }
            s.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return graph;
    }

    private static void fillAllPlaces() {
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