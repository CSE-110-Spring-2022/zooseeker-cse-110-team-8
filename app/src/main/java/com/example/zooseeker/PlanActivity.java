package com.example.zooseeker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
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
    //    Map<String, ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON("app/src/main/assets/sample_node_info.json");
        adapter = new SearchBarAdapter();
        adapter.setHasStableIds(true);



        Intent i = getIntent();
        List<ZooData.VertexInfo> zooDataVertex = ZooData.loadVertexInfoJSON(this,"sample_node_info.json");
        List<ZooData.VertexInfo> selected_exhibits = (List<ZooData.VertexInfo>)i.getSerializableExtra("selected");
        List<ZooData.EdgeInfo> zooDataEdge = ZooData.loadEdgeInfoJSON(this,"sample_edge_info.json");
        zooDataEdge.toString();
        Graph<String, IdentifiedWeightedEdge> g = ZooData.loadZooGraphJSON("sample_zoo_graph.json");
        GraphPath<String, IdentifiedWeightedEdge> path = DijkstraShortestPath.findPathBetween(g, selected_exhibits.get(1).name, selected_exhibits.get(3).name);
//
//        int i = 1;
//        for (IdentifiedWeightedEdge e : path.getEdgeList()) {
//            System.out.printf("  %d. Walk %.0f meters along %s from '%s' to '%s'.\n",
//                    i,
//                    g.getEdgeWeight(e),
//                    zooDataEdge.get(e.getId()).street,
//                 //   selected_exhibits.get(g.getEdgeSource(e).toString()).name,
//                    //    selected_exhibits.get(g.getEdgeTarget(e).toString()).name);
//            i++;
//        }

        recyclerView = findViewById(R.id.search_results_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setSearchResults(selected_exhibits);
    }


    public void onSearchClicked(View view)
    {
        Intent intent = new Intent(this, SearchBarActivity.class);
        startActivity(intent);
    }
}