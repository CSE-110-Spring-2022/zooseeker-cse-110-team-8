package com.example.zooseeker;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Search extends Activity {


    public static void displaySearchResult(Context context, String label) {
        // 2. Load the information about our nodes and edges...
        Map<String, ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON(context, "sample_node_info.json");

        for(ZooData.VertexInfo x : vInfo.values()) {
            if (x.tags.contains(label) || x.name.contains(label)) {
                System.out.printf(x.name + "\n");
            }
        }
    }

    public static List<ZooData.VertexInfo> getMatchingExhibits(Context context, String label) {
        // 2. Load the information about our nodes and edges...
        Map<String, ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON(context, "sample_node_info.json");

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