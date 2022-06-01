//package com.example.zooseeker;
//
//import android.content.Context;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.VisibleForTesting;
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//import androidx.room.TypeConverters;
//import androidx.sqlite.db.SupportSQLiteDatabase;
//
//import java.util.List;
//import java.util.concurrent.Executors;
//
//@Database(entities = {Plan.class}, version= 1)
//@TypeConverters({LanguageConverter.class})
//public abstract class PlanDatabase extends RoomDatabase {
//    private static PlanDatabase singleton = null;
//    public abstract PlanDAO PlanDAO();
//
//    public synchronized static PlanDatabase getSingleton(Context context){
//        if(singleton ==null){
//            singleton = PlanDatabase.makeDatabase(context);
//        }
//        return singleton;
//    }
//
//    @VisibleForTesting
//    public static void injectTestDatabase(PlanDatabase testDatabase){
//        if(singleton != null){
//            singleton.close();
//        }
//        singleton = testDatabase;
//    }
//
//    private static PlanDatabase makeDatabase(Context context){
//        return Room.databaseBuilder
//                (context.getApplicationContext(), PlanDatabase.class,"Plan_app.db")
//                .build();
//    }
//
//}
