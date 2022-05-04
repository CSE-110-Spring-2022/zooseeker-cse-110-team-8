package com.example.zooseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class planPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_page);
    }

    public void onBackClicked(View view){
        Intent intent = new Intent(this, SearchBarPageActivity.class);
        startActivity(intent);
    }
}