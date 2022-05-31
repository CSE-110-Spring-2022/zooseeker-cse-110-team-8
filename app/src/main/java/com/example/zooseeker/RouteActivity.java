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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteActivity extends AppCompatActivity {
    SearchBarDAO searchBarDAO = ZooDatabase.getSingleton(this).SearchBarDAO();
    private ZooDataViewModel zooDataViewModel;
    public RecyclerView recyclerView;
    public RouteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        adapter = new RouteAdapter();
        adapter.setHasStableIds(true);
        List<GraphPath<String, IdentifiedWeightedEdge>> Sorted_routes = new ArrayList<>();
        List<Double> distance = new ArrayList<>();




        double lowest_weight= 9999;
        String lowest_path="entrance_exit_gate";

        List<ZooData.VertexInfo> selected_exhibits = searchBarDAO.getAll();
        String currentlocation="entrance_exit_gate";
        Graph<String, IdentifiedWeightedEdge> g = ZooData.loadZooGraphJSON(this,"sample_zoo_graph.json");
        Map<String, ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON(this,"sample_vertex_info.json");
        Map<String, ZooData.EdgeInfo> eInfo = ZooData.loadEdgeInfoJSON(this,"sample_edge_info.json");
        GraphPath<String, IdentifiedWeightedEdge> low_path;
        ZooData.VertexInfo low_exhibit = null;
        List<ZooData.VertexInfo> Sorted_exhibits = new ArrayList<>();
        double route_distance = 0;
        // push all Plan Vertex id into map,
        // so we can use it to select shortest path from
        // current_location
        HashMap<String,Boolean> m = new HashMap<String, Boolean>();
        for(int i=0;i<selected_exhibits.size();i++){
            m.put(selected_exhibits.get(i).id,Boolean.TRUE);
        }


        for(int k=0;k<selected_exhibits.size();k++){
            GraphPath<String, IdentifiedWeightedEdge> currentpath;
            for(int i=0;i<selected_exhibits.size();i++){

                //if True we
                if(m.containsKey(selected_exhibits.get(i).id)) {
                    currentpath = DijkstraShortestPath.findPathBetween(g, currentlocation, selected_exhibits.get(i).id);

                    //get min
                    if (lowest_weight > currentpath.getWeight()) {
                        lowest_weight = currentpath.getWeight();
                        lowest_path = selected_exhibits.get(i).id;
                        low_exhibit = selected_exhibits.get(i);

                    }

                }

            }
            m.remove(lowest_path);

            //go from current -> dest
            currentpath = DijkstraShortestPath.findPathBetween(g, currentlocation, lowest_path);

            Sorted_routes.add(currentpath);
            route_distance += currentpath.getWeight();
            distance.add(route_distance);

            Sorted_exhibits.add(low_exhibit);
            System.out.println("weight: "+ currentpath.getWeight());
            System.out.println("now i am going from  "+ currentlocation+  " to: "+ lowest_path);
            currentlocation=lowest_path;
            lowest_weight=9999;

            int j = 1;
        }


        recyclerView = findViewById(R.id.route_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setRoutes(Sorted_exhibits,Sorted_routes,distance);
        //  disPlayRoute();

        //Trail code of activity
//        List<ZooData.VertexInfo> selected_exhibits = searchBarDAO.getAll();
//        recyclerView = findViewById(R.id.search_results_recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);


    }

    public void onDirectionClicked(View view) {
        Intent intent = new Intent( this, DirectionActivity.class);
        startActivity(intent);
    }
    public void disPlayRoute(){
        Graph<String, IdentifiedWeightedEdge> g = ZooData.loadZooGraphJSON(this,"sample_zoo_graph.json");
        List<String> selected_exhibits = searchBarDAO.getAllid();

        for(int i=0;i<selected_exhibits.size();i++)
         System.out.println(selected_exhibits.get(i));

      //  Intent intent = new Intent(this,RouteActivity.class);
     //   startActivity(intent);



//        GraphPath<String, IdentifiedWeightedEdge> path =
//                DijkstraShortestPath.findPathBetween(g, "entryes", "flamingo");
//        path.toString();

    }

}