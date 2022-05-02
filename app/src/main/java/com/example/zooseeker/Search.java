package com.example.zooseeker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Search {
    public static void displaySearchResult(String label) {
        // 2. Load the information about our nodes and edges...
        Map<String, ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON("app/src/main/assets/sample_node_info.json");

        for(ZooData.VertexInfo x : vInfo.values()) {
            if (x.tags.contains(label) || x.name.contains(label)) {
                System.out.printf(x.name + "\n");
            }
        }
    }

    public static List<ZooData.VertexInfo> getMatchingExhibits(String label) {
        // 2. Load the information about our nodes and edges...
        Map<String, ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON("app/src/main/assets/sample_node_info.json");

        // the map to hold our search results
        List<ZooData.VertexInfo> results = new ArrayList<ZooData.VertexInfo>();

        for(ZooData.VertexInfo x : vInfo.values()) {
            if (x.tags.contains(label) || x.name.contains(label)) {
                results.add(x);
            }
        }

        return results;
    }

}