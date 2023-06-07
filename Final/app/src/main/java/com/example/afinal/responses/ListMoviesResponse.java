package com.example.afinal.responses;

import com.example.afinal.movies.MoviesResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListMoviesResponse {
    @SerializedName("results")
    private List<MoviesResponse> moviesData;

    @SerializedName("total_pages")
    private int totalPages;

    public List<MoviesResponse> getMoviesData() {
        return moviesData;
    }

    public int getTotalPages() {
        return totalPages;
    }
}

