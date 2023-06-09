//package com.example.afinal.viewmodels;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;
//
//import com.example.afinal.databases.AppDatabase;
//import com.example.afinal.models.MoviesResponse;
//import com.example.afinal.models.TvshowsResponse;
//
//import java.util.List;
//
//public class FavoritesViewModel extends AndroidViewModel {
//
//    private final LiveData<List<MoviesResponse>> movies;
//    private final LiveData<List<TvshowsResponse>> tvShows;
//
//    public FavoritesViewModel(@NonNull Application application) {
//        super(application);
//
//        AppDatabase database = AppDatabase.getInstance(application);
//
//        movies = database.moviesDao().getAll();
//        tvShows = database.tvshowsDao().getAll();
//    }
//
//    public LiveData<List<MoviesResponse>> getMovies() {
//        return movies;
//    }
//
//    public LiveData<List<TvshowsResponse>> getTvShows() {
//        return tvShows;
//    }
//}
//
//
