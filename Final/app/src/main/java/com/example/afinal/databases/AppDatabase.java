package com.example.afinal.databases;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.afinal.models.MoviesResponse;
import com.example.afinal.models.TvshowsResponse;

@Database(entities = {MoviesResponse.class, TvshowsResponse.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase database;
    private static final String DATABASE_NAME = "ContentRoomDB";

    public synchronized static AppDatabase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME).allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
        }

        return database;
    }

    public abstract MoviesDao moviesDao();
    public abstract TvshowsDao tvshowsDao();
}
