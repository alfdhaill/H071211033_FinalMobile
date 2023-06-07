package com.example.afinal.tvshows;

import com.example.afinal.movies.MoviesResponse;
import com.google.gson.annotations.SerializedName;

public class TvshowsDataResponse {

    @SerializedName("results")
    private TvshowsResponse tvshowsData;

    public TvshowsResponse getTvshowsData() {
        return tvshowsData;
    }
}
