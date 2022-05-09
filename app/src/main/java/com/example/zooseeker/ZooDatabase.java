package com.example.zooseeker;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;
import java.util.concurrent.Executors;

@Database(entities={ZooData.VertexInfo.class}, version=1)
@TypeConverters({LanguageConverter.class})
public abstract class ZooDatabase extends RoomDatabase {
    // public com.example.zooseeker.SearchBarDAO SearchBarDAO;
    private static ZooDatabase singleton = null;
    public abstract SearchBarDAO SearchBarDAO();

    public synchronized static ZooDatabase getSingleton(Context context){
        if(singleton ==null){
            singleton = ZooDatabase.makeDatabase(context);
        }
        return singleton;
    }

    @VisibleForTesting
    public static void injectTestDatabase(ZooDatabase testDatabase){
        if(singleton != null){
            singleton.close();
        }
        singleton = testDatabase;
    }

    private static ZooDatabase makeDatabase(Context context){
        return Room.databaseBuilder(context, ZooDatabase.class,"zoo_app.db")
                .allowMainThreadQueries()
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db){
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(()->{
                            List<ZooData.VertexInfo> items = ZooData
                                    .loadVertexInfoJSON(context,"sample_node_info.json");
                            getSingleton(context).SearchBarDAO().insertAll(items);
                        });
                    }
                })
                .build();
    }
}
