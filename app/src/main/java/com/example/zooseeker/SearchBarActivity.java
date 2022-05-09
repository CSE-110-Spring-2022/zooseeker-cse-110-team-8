package com.example.zooseeker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Map;
public class SearchBarActivity extends AppCompatActivity {
    public static final String s_exhibit = "com.example.zooseeker.s_exhibit";
    private ZooDataViewModel zooDataViewModel;
    public RecyclerView recyclerView;
    public SearchBarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);


        SearchBarDAO searchBarDAO = ZooDatabase.getSingleton(this).SearchBarDAO();
        List<ZooData.VertexInfo> zooDataItems = searchBarDAO.getAll();

        adapter = new SearchBarAdapter();
        adapter.setHasStableIds(true);

        recyclerView = findViewById(R.id.search_results_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setSearchResults(zooDataItems);

//        zooDataViewModel = new ViewModelProvider(this).get(ZooDataViewModel.class);
//        zooDataViewModel.getZooDataAll().observe(this, new Observer<List<ZooData.VertexInfo>>() {
//            @Override
//            public void onChanged(List<ZooData.VertexInfo> vertexInfos) {
//                //update RecyclerView
//               adapter.setSearchResults(vertexInfos);
//            }
//        });

    }

    public void onPlanClicked(View view)
    {

        Intent intent = new Intent(this, PlanActivity.class);
        startActivity(intent);
    }

    private void saveExhibit(){

        List<ZooData.VertexInfo> selected = adapter.getAll();
        Intent intent = new Intent(this,PlanActivity.class);
        intent.putExtra("selected", (Parcelable) selected);
        startActivity(intent);
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

    public void onAddClicked(View view) {
        List<ZooData.VertexInfo> selected = adapter.getAll();
        Intent intent = new Intent(this,PlanActivity.class);
        intent.putExtra("selected", (Parcelable) selected);
        startActivity(intent);
    }
}