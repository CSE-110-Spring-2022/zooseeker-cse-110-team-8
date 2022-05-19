package com.example.zooseeker;


import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.w3c.dom.Text;

import static org.junit.Assert.*;

import java.util.List;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SearchBarActivityTest{
    ZooDatabase testDb;
    SearchBarDAO searchBarDAO;

    private static void forceLayout(RecyclerView recyclerView){
        recyclerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        recyclerView.layout(0,0,1080,2280);
    }
    @Before
    public void resetDataBase(){
        Context context = ApplicationProvider.getApplicationContext();
        testDb = Room.inMemoryDatabaseBuilder(context, ZooDatabase.class)
                .allowMainThreadQueries()
                .build();
        ZooDatabase.injectTestDatabase(testDb);
        List<ZooData.VertexInfo> todos = ZooData.loadVertexInfoJSONList(context, "sample_node_info.json");
        searchBarDAO = testDb.SearchBarDAO();
        searchBarDAO.insertAll(todos);
    }
    @Test
    public void testEditZooDataText() {
        String newText = "Ensure all test pass";
        ActivityScenario<SearchBarActivity> scenario
                = ActivityScenario.launch(SearchBarActivity.class);
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.moveToState(Lifecycle.State.STARTED);
        scenario.moveToState(Lifecycle.State.RESUMED);

        scenario.onActivity(activity ->{
            List<ZooData.VertexInfo> beforeTodoList = searchBarDAO.getAll();

            Button addTodoButton = activity.findViewById(R.id.add_to_plan);

            addTodoButton.performClick();

            List<ZooData.VertexInfo> afterTodoList = searchBarDAO.getAll();
            assertEquals(beforeTodoList.size(),afterTodoList.size());
         //   assertEquals(newText,afterTodoList.get(afterTodoList.size()-1).name);


        });


    }
}