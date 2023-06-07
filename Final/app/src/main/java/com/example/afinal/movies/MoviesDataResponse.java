package com.example.afinal.movies;

import com.example.afinal.movies.MoviesResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesDataResponse {

    @SerializedName("results")
    private MoviesResponse moviesData;
    public MoviesResponse getMoviesData() {
        return moviesData;
    }
}
