package com.example.zooseeker;


//import static org.junit.Assert.assertEquals;
//
//import android.content.Context;
//
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.platform.app.InstrumentationRegistry;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//@RunWith(AndroidJUnit4.class)
//public class ZooDatabasetest{
//    @Test
//    public void useAppContext() {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        assertEquals("com.example.zooseeker", appContext.getPackageName());
//    }
//}
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.zooseeker.SearchBarDAO;
import com.example.zooseeker.ZooDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(AndroidJUnit4.class)

public class ZooDatabasetest {
    private SearchBarDAO dao;
    private ZooDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ZooDatabase.class)
                .allowMainThreadQueries()
                .build();
        dao = db.SearchBarDAO();
    }

    @Test
    public void testInsert() {
        ZooData.VertexInfo.Kind kind = ZooData.VertexInfo.Kind.EXHIBIT;
        List<String> tags1 = new ArrayList<>();
        List<String> tags2 = new ArrayList<>();
        tags1.add("penguin");
        tags1.add("bird");
        tags2.add("otter");
        tags2.add("mammal");
        ZooData.VertexInfo item1 = new ZooData.VertexInfo("penguins", "penguins",kind,tags1);
        ZooData.VertexInfo item2 = new ZooData.VertexInfo("otters1", "otters", kind, tags2);
        ZooData.VertexInfo item3 = new ZooData.VertexInfo("otters2", "otters", kind, tags2);

        long id1 = dao.insert(item1);
        long id2 = dao.insert(item2);
        assertNotEquals(id1, id2);
    }
    @Test
    public void testGet() {
        List<String> tags1 = new ArrayList<>();
        tags1.add("penguin");
        tags1.add("bird");
        ZooData.VertexInfo.Kind kind = ZooData.VertexInfo.Kind.EXHIBIT;
        ZooData.VertexInfo insertedData = new ZooData.VertexInfo("penguins", "penguins", kind, tags1);
        long id = dao.insert(insertedData);

        ZooData.VertexInfo item = dao.get(id);
        assertEquals(id, item.id);
        assertEquals(insertedData.name, item.name);
        assertEquals(insertedData.kind, item.kind);
        assertEquals(insertedData.tags, item.tags);
    }
    @Test
    public void testUpdate() {
        List<String> tags1 = new ArrayList<>();
        tags1.add("penguin");
        tags1.add("bird");
        ZooData.VertexInfo.Kind kind = ZooData.VertexInfo.Kind.EXHIBIT;
        ZooData.VertexInfo item = new ZooData.VertexInfo("penguins", "penguins", kind, tags1);
        long id = dao.insert(item);
        item = dao.get(id);
        item.name = "penguins";
        int itemsUpdated = dao.update(item);
        assertEquals(1, itemsUpdated);
        item = dao.get(id);
        assertNotNull(item);
        assertEquals("penguins", item.name);
    }

    @Test
    public void testDelete() {
        List<String> tags1 = new ArrayList<>();
        tags1.add("penguin");
        tags1.add("bird");
        ZooData.VertexInfo.Kind kind = ZooData.VertexInfo.Kind.EXHIBIT;
        ZooData.VertexInfo item = new ZooData.VertexInfo("penguins" ,"penguins", kind, tags1);
        long id = dao.insert(item);
        item = dao.get(id);
        int itemsDeleted = dao.delete(item);
        assertEquals(1, itemsDeleted);
        assertNull(dao.get(id));

    }

    @After
    public void closeDB() throws IOException {
        db.close();
    }


}