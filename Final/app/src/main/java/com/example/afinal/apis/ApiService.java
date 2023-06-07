package com.example.afinal.apis;

import com.example.afinal.movies.MoviesDataResponse;
import com.example.afinal.movies.MoviesResponse;
import com.example.afinal.responses.ListMoviesResponse;
import com.example.afinal.responses.ListTvshowsResponse;
import com.example.afinal.tvshows.TvshowsDataResponse;
import com.example.afinal.tvshows.TvshowsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie/now_playing")
    Call<ListMoviesResponse> getMovies(@Query("language") String language,
                                       @Query("page") int page);

    @GET("tv/top_rated")
    Call<ListTvshowsResponse> getTvshows(@Query("language") String language,
                                          @Query("page") int page);

    @GET("movie/{movie_id}")
    Call<MoviesDataResponse> getMovieById(@Path("movie_id") int movieId,
                                          @Query("language") String language);

    @GET("tv/{tv_id}")
    Call<TvshowsDataResponse> getTvShowById(@Path("tv_id") int tvId,
                                            @Query("language") String language);
}
