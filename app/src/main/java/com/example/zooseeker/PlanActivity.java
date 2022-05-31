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

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PlanActivity extends AppCompatActivity {
    private ZooDataViewModel zooDataViewModel;
    SearchBarDAO searchBarDAO = ZooDatabase.getSingleton(this).SearchBarDAO();
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
        //  List<ZooData.VertexInfo> zooDataVertex = ZooData.loadVertexInfoJSON(this,"sample_node_info.json");
        //    List<ZooData.VertexInfo> selected_exhibits = (List<ZooData.VertexInfo>)i.getSerializableExtra("selected");
        List<ZooData.VertexInfo> selected_exhibits = searchBarDAO.getAll();
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


//   Getting Shortest path when Route button get clicked
    public void onRouteClicked(View view) {
      //  Intent intent = new Intent( this, RouteActivity.class);

        double lowest_weight= 9999;
        String lowest_path="entrance_exit_gate";

        List<String> selected_exhibits = searchBarDAO.getAllid();
        String currentlocation="entrance_exit_gate";
        Graph<String, IdentifiedWeightedEdge> g = ZooData.loadZooGraphJSON(this,"sample_zoo_graph.json");
        Map<String, ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON(this,"sample_vertex_info.json");
        Map<String, ZooData.EdgeInfo> eInfo = ZooData.loadEdgeInfoJSON(this,"sample_edge_info.json");

        //push all Plan Vertex id into map,
        // so we can use it to select shortest path from
        // current_location
        HashMap<String,Boolean> m = new HashMap<String, Boolean>();
        for(int i=0;i<selected_exhibits.size();i++){
            m.put(selected_exhibits.get(i),Boolean.TRUE);
        }


        for(int k=0;k<selected_exhibits.size();k++){
            GraphPath<String, IdentifiedWeightedEdge> currentpath;
            for(int i=0;i<selected_exhibits.size();i++){

                //if True we
                if(m.containsKey(selected_exhibits.get(i))) {
                    currentpath = DijkstraShortestPath.findPathBetween(g, currentlocation, selected_exhibits.get(i));

                    //get min
                    if (lowest_weight > currentpath.getWeight()) {
                        lowest_weight = currentpath.getWeight();
                        lowest_path = selected_exhibits.get(i);
                    }

                }

            }
            m.remove(lowest_path);



            //go from current -> dest
            currentpath = DijkstraShortestPath.findPathBetween(g, currentlocation, lowest_path);
            System.out.println("weight: "+ currentpath.getWeight());
            System.out.println("now i am going from  "+ currentlocation+  " to: "+ lowest_path);

            currentlocation=lowest_path;
            lowest_weight=9999;



            int j = 1;

            for (IdentifiedWeightedEdge e : currentpath.getEdgeList()) {
                System.out.printf("  %d. Walk %.0f meters along %s from '%s' to '%s'.\n",
                        j,
                        g.getEdgeWeight(e),
                        eInfo.get(e.getId()).street,
                        vInfo.get(g.getEdgeSource(e).toString()).name,
                        vInfo.get(g.getEdgeTarget(e).toString()).name);
                j++;
            }
        }


    //adapter.setSearchResults();
     //   startActivity(intent);
    }

    /*
     * clear the selected exhibit in the database and update the adapter with
     * empty selected exhibit.
     */
    public void onClearClicked(View view) {
        searchBarDAO.deleteAllZooData();
        List<ZooData.VertexInfo> selected_exhibits = searchBarDAO.getAll();
        adapter.setSearchResults(selected_exhibits);

    }
}