package com.example.afinal.movies;

import com.example.afinal.models.MoviesResponse;
import com.google.gson.annotations.SerializedName;

public class MoviesDataResponse {

    @SerializedName("results")
    private MoviesResponse moviesData;
    public MoviesResponse getMoviesData() {
        return moviesData;
    }
}
