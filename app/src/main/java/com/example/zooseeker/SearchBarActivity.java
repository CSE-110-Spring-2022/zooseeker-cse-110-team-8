package com.example.zooseeker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
public class SearchBarActivity extends AppCompatActivity {
    public static final String s_exhibit = "com.example.zooseeker.s_exhibit";
    SearchBarDAO searchBarDAO;
    private ZooDataViewModel zooDataViewModel;
    public RecyclerView recyclerView;
    public SearchBarAdapter adapter;

    public List<ZooData.VertexInfo> plan;
    public Map<String, ZooData.VertexInfo> zooDataItemsNotInDatabaseMap;
    public List<ZooData.VertexInfo> zooDataItemsNotInDatabaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);


        searchBarDAO = ZooDatabase.getSingleton(this).SearchBarDAO();

        zooDataItemsNotInDatabaseMap = ZooData.loadVertexInfoJSON(this, "sample_vertex_info.json");
        zooDataItemsNotInDatabaseList = ZooData.loadVertexInfoJSONList(this, "sample_vertex_info.json");
        try {
            List<ZooData.VertexInfo> plan = searchBarDAO.getAll();
        }
        catch (Exception e) {
            List<ZooData.VertexInfo> plan = Collections.emptyList();
        }

        int index = 0;

        // removes intersections and gates from the list
        while (index < zooDataItemsNotInDatabaseList.size()) {
            if (zooDataItemsNotInDatabaseList.get(index).kind == ZooData.VertexInfo.Kind.INTERSECTION ||
                    zooDataItemsNotInDatabaseList.get(index).kind == ZooData.VertexInfo.Kind.GATE) {
                zooDataItemsNotInDatabaseList.remove(index);
            }
            else {
                index++;
            }
        }

        adapter = new SearchBarAdapter();
        adapter.setHasStableIds(true);

        recyclerView = findViewById(R.id.search_results_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setSearchResults(zooDataItemsNotInDatabaseList);


/*
        zooDataViewModel = new ViewModelProvider(this).get(ZooDataViewModel.class);
        zooDataViewModel.getZooDataAll().observe(this, new Observer<List<ZooData.VertexInfo>>() {
            @Override
            public void onChanged(List<ZooData.VertexInfo> vertexInfos) {
                //update RecyclerView
               adapter.setSearchResults(vertexInfos);
            }
        });
        String start = "entrance_exit_gate";
        String goal = "elephant_odyssey";
        String pa= "sample_zoo_graph.json";
        1. Load the graph...
        Graph<String, IdentifiedWeightedEdge> g = ZooData.loadZooGraphJSON("sample_zoo_graph.json");
        GraphPath<String, IdentifiedWeightedEdge> currentpath = DijkstraShortestPath.findPathBetween(g, start, goal);
        currentpath.getWeight();
        2. Load the information about our nodes and edges...
        Map<String, ZooData.VertexInfo> vInfoMap = ZooData.loadVertexInfoJSON(this,"sample_node_info.json");
        Map<String, ZooData.VertexInfo> eInfoMap = ZooData.loadEdgeInfoJSON(this,"sample_edge_info.json");
        System.out.printf("The shortest path from '%s' to '%s' is:\n", start, goal);
        int i = 1;
        for (IdentifiedWeightedEdge e : currentpath.getEdgeList()) {
            System.out.printf("  %d. Walk %.0f meters along %s from '%s' to '%s'.\n",
                    i,
                      g.getEdgeWeight(e),
                      eInfo.get(e.getId()).street,
                      vInfo.get(g.getEdgeSource(e).toString()).name,
                      vInfo.get(g.getEdgeTarget(e).toString()).name);
            i++;
        }
*/

//        String start = "entrance_exit_gate";
//        String goal = "elephant_odyssey";
//        String pa= "sample_zoo_graph.json";
//
//        // 1. Load the graph...
//          Graph<String, IdentifiedWeightedEdge> g = ZooData.loadZooGraphJSON("sample_zoo_graph.json");
//          GraphPath<String, IdentifiedWeightedEdge> currentpath = DijkstraShortestPath.findPathBetween(g, start, goal);
//          currentpath.getWeight();
//
//        // 2. Load the information about our nodes and edges...
//          List<ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON(this,"sample_node_info.json");
//          List<ZooData.EdgeInfo> eInfo = ZooData.loadEdgeInfoJSON(this,"sample_edge_info.json");
//        List<ZooData.VertexInfo> selected =

//
//        System.out.printf("The shortest path from '%s' to '%s' is:\n", start, goal);
//
//        int i = 1;

        //for (IdentifiedWeightedEdge e : currentpath.getEdgeList()) {
        //    System.out.printf("  %d. Walk %.0f meters along %s from '%s' to '%s'.\n",
//                    i,
//                      g.getEdgeWeight(e),
//                      eInfo.get(e.getId()).street,
//                      vInfo.get(g.getEdgeSource(e).toString()).name,
//                      vInfo.get(g.getEdgeTarget(e).toString()).name);
//            i++;
        //}
    }

    public void onPlanClicked(View view)
    {
        Intent intent = new Intent(this,PlanActivity.class);
        List<ZooData.VertexInfo> exhibits = adapter.getAll();
        for(ZooData.VertexInfo x: exhibits)
        {
            if (plan.contains(x)) {
                searchBarDAO.insert(x);
            }
        }
//        intent.putExtra("selected", (Serializable) selected);
        startActivity(intent);
    }

    public void onCheckboxClicked(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            if (!(zooDataItemsNotInDatabaseList.contains(zooDataItemsNotInDatabaseList.get(view.getId())))) {
                plan.add(0, zooDataItemsNotInDatabaseList.get(view.getId()));
            }
        }
        else {
            if (zooDataItemsNotInDatabaseList.contains(zooDataItemsNotInDatabaseList.get(view.getId()))) {
                plan.remove(plan.indexOf(zooDataItemsNotInDatabaseList.get(view.getId())));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
/*
    public void onAddClicked(View view) {
        List<ZooData.VertexInfo> selected = adapter.getAll();
        for(ZooData.VertexInfo x: selected) {
            searchBarDAO.insert(x);
        }
        Intent intent = new Intent(this,PlanActivity.class);
        // intent.putExtra("selected", (Serializable) selected);
        startActivity(intent);
    }*/
}