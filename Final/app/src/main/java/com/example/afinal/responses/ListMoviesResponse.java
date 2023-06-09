package com.example.afinal.responses;

import com.example.afinal.models.MoviesResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListMoviesResponse {

    @SerializedName("results")
    private List<MoviesResponse> moviesData;

    public List<MoviesResponse> getMoviesData() {
        return moviesData;
    }
}

