package com.example.afinal.responses;

import com.example.afinal.movies.MoviesResponse;
import com.example.afinal.tvshows.TvshowsResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListDataResponse {
    @SerializedName("results")
    private List<MoviesResponse> moviesData;

    @SerializedName("results")
    private List<TvshowsResponse> tvseriesData;

    public List<MoviesResponse> getMoviesData() {
        return moviesData;
    }

    public List<TvshowsResponse> getTvseriesData() {
        return tvseriesData;
    }
}
