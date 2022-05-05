package com.example.zooseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Map;

public class SearchBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);
        Map<String, ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON(this, "sample_node_info.json");

    }

    public void onPlanClicked(View view)
    {
        Intent intent = new Intent(this, PlanActivity.class);
        startActivity(intent);
    }
}