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

public class RouteActivity extends AppCompatActivity {
    SearchBarDAO searchBarDAO;
    private ZooDataViewModel zooDataViewModel;
    public RecyclerView recyclerView;
    public SearchBarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

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