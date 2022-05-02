package com.example.zooseeker;

import java.util.Map;

public class Search {
    public static void displaySearchResult(String label) {
        // 2. Load the information about our nodes and edges...
        Map<String, ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON("app/src/main/assets/sample_node_info.json");
        // Map<String, ZooData.EdgeInfo> eInfo = ZooData.loadEdgeInfoJSON("sample_edge_info.json");

        for(ZooData.VertexInfo x : vInfo.values()) {
            if (x.tags.contains(label) || x.name.contains(label)) {
                System.out.printf(x.name + "\n");
            }
        }
    }
}