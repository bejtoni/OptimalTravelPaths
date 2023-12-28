package org.example;

import java.util.HashMap;
import java.util.Map;

public class Places {
    private final String shortcode;
    private final String fullname;

    private static Map<String, String> allPlacesMap = new HashMap<>();

    public Places(String shortcode, String fullname){

        this.shortcode = shortcode;
        this.fullname = fullname;
    }

    public static void add(String shortcode, String name) {
        allPlacesMap.put(shortcode, name);
    }

    public static Map<String, String> getAllPlacesMap() {
        return allPlacesMap;
    }

    public String getFullname() {
        return fullname;
    }

    public String getShortcode() {
        return shortcode;
    }
}
