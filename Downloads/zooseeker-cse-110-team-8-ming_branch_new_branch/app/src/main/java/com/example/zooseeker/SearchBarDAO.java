package com.example.zooseeker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SearchBarDAO {
    @Insert
    long insert(ZooData.VertexInfo searchResult);

    @Insert
    List<Long> insertAll(List<ZooData.VertexInfo> searchResult);

    @Query("SELECT * FROM `search_result` WHERE `id`=:id")
    ZooData.VertexInfo get(long id);

    @Query("SELECT * FROM `search_result` ORDER BY `id`" )
    List<ZooData.VertexInfo> getAll();

    @Query("DELETE FROM `search_result`")
    void deleteAllZooData();

    @Update
    int update(ZooData.VertexInfo searchResult);

    @Delete
    int delete(ZooData.VertexInfo searchResult);



    @Query("SELECT * FROM `search_result` ORDER BY `id`")
    LiveData<List<ZooData.VertexInfo>> getAllLive();
}
