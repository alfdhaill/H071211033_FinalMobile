package com.example.afinal.apis;

import com.example.afinal.responses.ListMoviesResponse;
import com.example.afinal.responses.ListTvshowsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie/now_playing")
    Call<ListMoviesResponse> getMovies(@Query("language") String language,
                                       @Query("page") int page);

    @GET("tv/top_rated")
    Call<ListTvshowsResponse> getTvshows(@Query("language") String language,
                                          @Query("page") int page);
}
