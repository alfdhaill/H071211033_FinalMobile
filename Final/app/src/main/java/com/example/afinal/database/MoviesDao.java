package com.example.afinal.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.afinal.models.MoviesResponse;

import java.util.List;

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MoviesResponse moviesResponse);

    @Query("SELECT * FROM moviesresponse")
    List<MoviesResponse> getAll();

    @Query("SELECT EXISTS (SELECT 1 FROM moviesresponse WHERE id = :id)")
    boolean isMovieExists(int id);

    @Delete
    void delete(MoviesResponse moviesResponse);
}
