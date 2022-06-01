package com.example.zooseeker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class PlanActivity extends AppCompatActivity {
    private ZooDataViewModel zooDataViewModel;
    SearchBarDAO searchBarDAO = ZooDatabase.getSingleton(this).SearchBarDAO();
    public RecyclerView recyclerView;
    public TextView text;
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
        text = findViewById(R.id.total_exhibit_count);
        String size = String.valueOf(selected_exhibits.size());
        text.setText(size);
        adapter.setSearchResults(selected_exhibits);

    }



    public void onSearchClicked(View view)
    {
        Intent intent = new Intent(this, SearchBarActivity.class);
        startActivity(intent);

    }


//   Getting Shortest path when Route button get clicked
    public void onRouteClicked(View view) {
      Intent intent = new Intent( this, RouteActivity.class);
      startActivity(intent);
    }

    /*
     * clear the selected exhibit in the database and update the adapter with
     * empty selected exhibit.
     */
    public void onClearClicked(View view) {
        searchBarDAO.deleteAllZooData();
        List<ZooData.VertexInfo> selected_exhibits = searchBarDAO.getAll();
        adapter.setSearchResults(selected_exhibits);
        String size = String.valueOf(selected_exhibits.size());
        text.setText(size);
    }
}