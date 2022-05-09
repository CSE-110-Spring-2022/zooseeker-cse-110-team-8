package com.example.zooseeker;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ZooDataViewModel extends AndroidViewModel {
    private ZooDataRepository repository;
    private LiveData<List<ZooData.VertexInfo>> zooDataAll;

    public ZooDataViewModel(@NonNull Application application) {
        super(application);
        repository = new ZooDataRepository(application);
        zooDataAll = repository.getZooDataAll();
    }

    public void insert(ZooData.VertexInfo exhibit) {
        repository.insert(exhibit);
    }

    public void update(ZooData.VertexInfo exhibit){
        repository.update(exhibit);
    }
    public void delete(ZooData.VertexInfo exhibit){
        repository.delete(exhibit);
    }
    public void deleteAllExhibits(){
        repository.deleteAllExhibits();
    }
    public LiveData<List<ZooData.VertexInfo>> getZooDataAll(){
        return zooDataAll;
    }
}