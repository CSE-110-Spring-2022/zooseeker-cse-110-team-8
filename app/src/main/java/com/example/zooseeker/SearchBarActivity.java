package com.example.zooseeker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Map;

public class SearchBarActivity extends AppCompatActivity {

    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);

        SearchBarAdapter adapter = new SearchBarAdapter();
        adapter.setHasStableIds(true);

        recyclerView = findViewById(R.id.search_results);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setSearchResults(ZooData.loadVertexInfoJSON(this, "sample_node_info.json"));
    }

    public void onPlanClicked(View view)
    {
        Intent intent = new Intent(this, PlanActivity.class);
        startActivity(intent);
    }
}