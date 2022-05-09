package com.example.zooseeker;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.test.core.app.ApplicationProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.nio.json.JSONImporter;

public class ZooData {

    @Entity(tableName = "search_result")
    public static class VertexInfo implements Serializable {

        public static enum Kind {
            // The SerializedName annotation tells GSON how to convert
            // from the strings in our JSON to this Enum.
            @SerializedName("gate") GATE,
            @SerializedName("exhibit") EXHIBIT,
            @SerializedName("intersection") INTERSECTION

        }

        @PrimaryKey(autoGenerate = true)
        public long id;

        @NonNull
        public String name;
        public Kind kind;
        public List<String> tags;

        VertexInfo(@NonNull String name, Kind kind, List<String> tags) {
            this.name = name;
            this.kind = kind;
            this.tags = tags;
        }

        @Override
        public String toString() {
            return "VertexInfo{" +
                    "id='" + id + '\'' +
                    ", kind=" + kind +
                    ", name='" + name + '\'' +
                    ", tags=" + tags +
                    '}';
        }
    }

    public static class EdgeInfo {
        public String id;
        public String street;
    }

//    public static Map<String, ZooData.VertexInfo> loadVertexInfoJSON(String path) {
//        InputStream inputStream = App.class.getClassLoader().getResourceAsStream(path);
//        Reader reader = new InputStreamReader(inputStream);
//
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<ZooData.VertexInfo>>(){}.getType();
//        List<ZooData.VertexInfo> zooData = gson.fromJson(reader, type);
//
//        // This code is equivalent to:
//        //
//        // Map<String, ZooData.VertexInfo> indexedZooData = new HashMap();
//        // for (ZooData.VertexInfo datum : zooData) {
//        //   indexedZooData[datum.id] = datum;
//        // }
//        //
//        Map<String, ZooData.VertexInfo> indexedZooData = zooData
//                .stream()
//                .collect(Collectors.toMap(v -> v.id, datum -> datum));
//
//        return indexedZooData;
//    }

    public static List<ZooData.VertexInfo> loadVertexInfoJSON(Context context, String path) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Reader reader = new InputStreamReader(inputStream);

        Gson gson = new Gson();
        Type type = new TypeToken<List<ZooData.VertexInfo>>(){}.getType();
        List<ZooData.VertexInfo> zooData = gson.fromJson(reader, type);

//        List<ZooData.VertexInfo> indexedZooData = new ArrayList<>(zooData);

        // This code is equivalent to:
        //
        // Map<String, ZooData.VertexInfo> indexedZooData = new HashMap();
        // for (ZooData.VertexInfo datum : zooData) {
        //   indexedZooData[datum.id] = datum;
        // }
        //

        //Map<String, ZooData.VertexInfo> indexedZooData = zooData
        //        .stream()
         //       .collect(Collectors.toMap(v -> String.valueOf(v.id), datum -> datum));

        return zooData;
    }


    public static List<ZooData.EdgeInfo> loadEdgeInfoJSON(Context context, String path) {
        // InputStream inputStream = App.class.getClassLoader().getResourceAsStream(path);
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Reader reader = new InputStreamReader(inputStream);

        Gson gson = new Gson();
        Type type = new TypeToken<List<ZooData.EdgeInfo>>(){}.getType();
        List<ZooData.EdgeInfo> zooData = gson.fromJson(reader, type);

        return zooData;
    }

    public static Graph<String, IdentifiedWeightedEdge> loadZooGraphJSON(String path) {
        // Create an empty graph to populate.
        Graph<String, IdentifiedWeightedEdge> g = new DefaultUndirectedWeightedGraph<>(IdentifiedWeightedEdge.class);

        // Create an importer that can be used to populate our empty graph.
        JSONImporter<String, IdentifiedWeightedEdge> importer = new JSONImporter<>();

        // We don't need to convert the vertices in the graph, so we return them as is.
        importer.setVertexFactory(v -> v);

        // We need to make sure we set the IDs on our edges from the 'id' attribute.
        // While this is automatic for vertices, it isn't for edges. We keep the
        // definition of this in the IdentifiedWeightedEdge class for convenience.
        importer.addEdgeAttributeConsumer(IdentifiedWeightedEdge::attributeConsumer);

        // On Android, you would use context.getAssets().open(path) here like in Lab 5.
        // InputStream inputStream = App.class.getClassLoader().getResourceAsStream(path);
//        Context context = ApplicationProvider.getApplicationContext();
//        InputStream inputStream = null;
//        try {
//            inputStream = context.getAssets().open(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        File initialFile = new File(path);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(initialFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Reader reader = new InputStreamReader(inputStream);

        // And now we just import it!
        importer.importGraph(g, reader);

        return g;
    }
}