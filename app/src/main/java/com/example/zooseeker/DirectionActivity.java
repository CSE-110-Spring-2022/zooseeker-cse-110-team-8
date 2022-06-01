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
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 *
 */
public class DirectionActivity extends AppCompatActivity {
    SearchBarDAO searchBarDAO = ZooDatabase.getSingleton(this).SearchBarDAO();
    Plan currentPlan;
    private ZooDataViewModel zooDataViewModel;
    public RecyclerView recyclerView;
    public DirectionAdapter adapter;
    public TextView text;

    /*
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        adapter = new DirectionAdapter();
        adapter.setHasStableIds(true);

        currentPlan = (Plan) getIntent().getSerializableExtra("current_plan");
        if(currentPlan.sorted_routes !=null) {
            Graph<String, IdentifiedWeightedEdge> g = ZooData.loadZooGraphJSON(this, "sample_zoo_graph.json");
            Map<String, ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON(this, "sample_vertex_info.json");
            Map<String, ZooData.EdgeInfo> eInfo = ZooData.loadEdgeInfoJSON(this, "sample_edge_info.json");
            List<GraphPath<String, IdentifiedWeightedEdge>> Sorted_routes = new ArrayList<>();
            List<String> direction = new ArrayList<>();
            Sorted_routes = currentPlan.sorted_routes;
            String sdir = "";
            int j = 1;
            for (IdentifiedWeightedEdge e : Sorted_routes.get(currentPlan.index).getEdgeList()) {
                System.out.printf("  %d. Walk %.0f meters along %s from '%s' to '%s'.\n",
                        j,
                        g.getEdgeWeight(e),
                        eInfo.get(e.getId()).street,
                        vInfo.get(g.getEdgeSource(e).toString()).name,
                        vInfo.get(g.getEdgeTarget(e).toString()).name);
                sdir = String.valueOf(j) + " Walk " + g.getEdgeWeight(e)
                        + " of meters along " + eInfo.get(e.getId()).street
                        + " from " + vInfo.get(g.getEdgeSource(e).toString()).name
                        + " to " + vInfo.get(g.getEdgeTarget(e).toString()).name;
                direction.add(sdir);
                j++;
            }

            recyclerView = findViewById(R.id.direction_recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            text = findViewById(R.id.from_to_exhibit);
            String from_to = "";
            if(currentPlan.index < currentPlan.sorted_routes.size()-1) {
                from_to = currentPlan.plan.get(currentPlan.index).name + " to " + currentPlan.plan.get(currentPlan.index + 1).name;
            }
            else
            {
                from_to = currentPlan.plan.get(currentPlan.index-1).name + " to " + currentPlan.plan.get(currentPlan.index).name;

            }
            text.setText(from_to);
            adapter.setDirections(currentPlan.plan, currentPlan.sorted_routes, currentPlan.distance, direction);
        }
    }


    public void onDetailedClicked(View view) {
    }

    public void onbrefClicked(View view) {
    }



    public void onNextClicked(View view) {
        if(currentPlan.index < currentPlan.sorted_routes.size()-1) {
            currentPlan.index += 1;
            Intent intent = new Intent( this, DirectionActivity.class);
            intent.putExtra("current_plan", currentPlan);
            startActivity(intent);
        }
    }

    public void onPreviousClicked(View view) {
        if(currentPlan.index > 0) {
            currentPlan.index -= 1;
            Intent intent = new Intent( this, DirectionActivity.class);
            intent.putExtra("current_plan", currentPlan);
            startActivity(intent);
        }
    }
}