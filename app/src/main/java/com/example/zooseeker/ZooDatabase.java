package com.example.zooseeker;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Database(entities={ZooData.VertexInfo.class}, version=1)
@TypeConverters({LanguageConverter.class})
public abstract class ZooDatabase extends RoomDatabase {
    // public com.example.zooseeker.SearchBarDAO SearchBarDAO;

    public abstract SearchBarDAO SearchBarDAO();
}
