package com.example.afinal.responses;

import com.example.afinal.tvshows.TvshowsResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListTvshowsResponse {

    @SerializedName("results")
    private List<TvshowsResponse> tvshowsData;

    public List<TvshowsResponse> getTvshowsData() {
        return tvshowsData;
    }
}
