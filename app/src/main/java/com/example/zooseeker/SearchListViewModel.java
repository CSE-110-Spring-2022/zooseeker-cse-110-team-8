package com.example.zooseeker;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SearchListViewModel extends AndroidViewModel {
    private LiveData<List<ZooData.VertexInfo>> zooDataItems;
    private final SearchBarDAO searchBarDAO;

    public SearchListViewModel(@NonNull Application application) {
        super(application);
        Context context = getApplication().getApplicationContext();
        ZooDatabase db = ZooDatabase.getSingleton(context);
        searchBarDAO = db.SearchBarDAO();
    }

    public LiveData<List<ZooData.VertexInfo>> getZooDataItems(){
        if(zooDataItems == null){
            loadUsers();
        }
        return zooDataItems;
    }

    private void loadUsers(){
        zooDataItems = searchBarDAO.getAllLive();
    }

}
