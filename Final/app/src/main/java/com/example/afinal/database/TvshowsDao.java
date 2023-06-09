package com.example.afinal.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.afinal.models.TvshowsResponse;

import java.util.List;

@Dao
public interface TvshowsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TvshowsResponse tvshowsResponse);

    @Query("SELECT * FROM tvshowsresponse")
    List<TvshowsResponse> getAll();

    @Query("SELECT EXISTS (SELECT 1 FROM tvshowsresponse WHERE id = :id)")
    boolean isTvshowExists(int id);

    @Delete
    void delete(TvshowsResponse tvshowsResponse);
}
