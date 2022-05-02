package com.example.zooseeker;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.app.Activity;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import java.util.List;
import java.util.Map;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleUnitTest {


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }



    @Test
    public void is_empty() {
        Map<String, ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON("src/main/assets/sample_node_info.json");
        assertFalse(vInfo.values().isEmpty());
    }
    @Test
    public void searched() {
      //  Search s = new Search();
        Map<String, ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON("src/main/assets/sample_node_info.json");
        boolean a=false;
        for(ZooData.VertexInfo x : vInfo.values()) {
            if (x.tags.contains("mammal") || x.name.contains("mammal")){
                a=true;
            }
        }
        assertTrue(a);
    }
    @Test
    public void Edge_exist(){

    }

}

