package com.example.zooseeker;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ZooDataRepository extends AndroidViewModel {
    public SearchBarDAO searchBarDAO;
    private LiveData<List<ZooData.VertexInfo>> zooDataAll;

    public ZooDataRepository(@NonNull Application application) {
        super(application);
        Context context = getApplication().getApplicationContext();
        ZooDatabase db = ZooDatabase.getSingleton(context);
        searchBarDAO = db.SearchBarDAO();
    }

    public void insert(ZooData.VertexInfo exhibit) {
        new InsertZooDataAsyncTask(searchBarDAO).execute(exhibit);
    }

    public void update(ZooData.VertexInfo exhibit) {
        new UpDateZooDataAsyncTask(searchBarDAO).execute(exhibit);
    }

    public void delete(ZooData.VertexInfo exhibit) {
        new DeleteZooDataAsyncTask(searchBarDAO).execute(exhibit);
    }

    public void deleteAllExhibits() {
        new DeleteAllZooDataAsyncTask(searchBarDAO).execute();
    }

    public LiveData<List<ZooData.VertexInfo>> getZooDataAll() {
        if (zooDataAll == null) {
            loadUsers();
        }
        return zooDataAll;
    }

    private static class InsertZooDataAsyncTask extends AsyncTask<ZooData.VertexInfo, Void, Void> {
        private SearchBarDAO searchBarDAO;

        private InsertZooDataAsyncTask(SearchBarDAO searchBarDAO) {
            this.searchBarDAO = searchBarDAO;
        }

        @Override
        protected Void doInBackground(ZooData.VertexInfo... vertexInfos) {
            searchBarDAO.insert(vertexInfos[0]);
            return null;
        }
    }

    private static class UpDateZooDataAsyncTask extends AsyncTask<ZooData.VertexInfo, Void, Void> {
        private SearchBarDAO searchBarDAO;

        private UpDateZooDataAsyncTask(SearchBarDAO searchBarDAO) {
            this.searchBarDAO = searchBarDAO;
        }

        @Override
        protected Void doInBackground(ZooData.VertexInfo... vertexInfos) {
            searchBarDAO.update(vertexInfos[0]);
            return null;
        }
    }

    private static class DeleteZooDataAsyncTask extends AsyncTask<ZooData.VertexInfo, Void, Void> {
        private SearchBarDAO searchBarDAO;

        private DeleteZooDataAsyncTask(SearchBarDAO searchBarDAO) {
            this.searchBarDAO = searchBarDAO;
        }

        @Override
        protected Void doInBackground(ZooData.VertexInfo... vertexInfos) {
            searchBarDAO.delete(vertexInfos[0]);
            return null;
        }
    }

    private static class DeleteAllZooDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private SearchBarDAO searchBarDAO;

        private DeleteAllZooDataAsyncTask(SearchBarDAO searchBarDAO) {
            this.searchBarDAO = searchBarDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            searchBarDAO.deleteAllZooData();
            return null;
        }
    }

    private void loadUsers() {
        zooDataAll = searchBarDAO.getAllLive();
    }
}
