package com.example.zooseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.Map;


public class PlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
    //    Map<String, ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON("app/src/main/assets/sample_node_info.json");
    }


    public void onSearchClicked(View view)
    {
        Intent intent = new Intent(this, SearchBarActivity.class);
        startActivity(intent);
    }
}