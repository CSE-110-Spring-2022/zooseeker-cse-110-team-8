package com.example.zooseeker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.List;
import java.util.Map;


public class PlanActivity extends AppCompatActivity {
    private ZooDataViewModel zooDataViewModel;
    public RecyclerView recyclerView;
    public SearchBarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
    //    Map<String, ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON("app/src/main/assets/sample_vertex_info.json");
        adapter = new SearchBarAdapter();
        adapter.setHasStableIds(true);



        Intent i = getIntent();
      //  List<ZooData.VertexInfo> zooDataVertex = ZooData.loadVertexInfoJSON(this,"sample_vertex_info.json");
        List<ZooData.VertexInfo> selected_exhibits = (List<ZooData.VertexInfo>)i.getSerializableExtra("selected");
        recyclerView = findViewById(R.id.search_results_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setSearchResults(selected_exhibits);


//        1. Load the graph...
     //   GraphPath<String, IdentifiedWeightedEdge> currentpath;
     //   Graph<String, IdentifiedWeightedEdge> g = ZooData.loadZooGraphJSON("sample_zoo_graph.json");



//
//        2. Load the information about our nodes and edges...
//        Map<String, ZooData.VertexInfo> vInfoMap = ZooData.loadVertexInfoJSON(this,"sample_node_info.json");
//        Map<String, ZooData.VertexInfo> eInfoMap = ZooData.loadEdgeInfoJSON(this,"sample_edge_info.json");
//
//        System.out.printf("The shortest path from '%s' to '%s' is:\n", start, goal);
//
//        int i = 1;
//        for (IdentifiedWeightedEdge e : currentpath.getEdgeList()) {
//            System.out.printf("  %d. Walk %.0f meters along %s from '%s' to '%s'.\n",
//                    i,
//                    g.getEdgeWeight(e),
//                    eInfo.get(e.getId()).street,
//                    vInfo.get(g.getEdgeSource(e).toString()).name,
//                    vInfo.get(g.getEdgeTarget(e).toString()).name);
//            i++;
    }


    public void onSearchClicked(View view)
    {
        Intent intent = new Intent(this, SearchBarActivity.class);
        startActivity(intent);
    }

    public void onRouteClicked(View view) {
        Intent intent = new Intent( this, RouteActivity.class);
        startActivity(intent);
    }
}