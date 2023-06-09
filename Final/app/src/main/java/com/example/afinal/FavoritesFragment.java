package com.example.afinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.afinal.adapters.FavoriteMoviesAdapter;
import com.example.afinal.adapters.FavoriteTvshowsAdapter;
import com.example.afinal.databases.AppDatabase;
import com.example.afinal.models.MoviesResponse;
import com.example.afinal.models.TvshowsResponse;
import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment {

    Handler handler;
    ExecutorService executorService;
    List<MoviesResponse> moviesResponses;
    List<TvshowsResponse> tvshowsResponses;
    FavoriteMoviesAdapter favoriteMoviesAdapter;
    FavoriteTvshowsAdapter favoriteTvshowsAdapter;
    AppDatabase database;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.getSupportActionBar().setTitle(getString(R.string.favorites));
        }

        TabLayout tabLayout = view.findViewById(R.id.tl);
        RecyclerView recyclerView = view.findViewById(R.id.rv_favorites);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(FavoritesFragment.this.getContext()));

        database = AppDatabase.getInstance(FavoritesFragment.this.getContext());

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();

                if (position == 0) {

                    executorService.execute(() -> {

                        moviesResponses = database.moviesDao().getAll();

                        handler.post(() -> {

                            favoriteMoviesAdapter = new FavoriteMoviesAdapter(moviesResponses);
                            recyclerView.setAdapter(favoriteMoviesAdapter);
                        });
                    });
                } else if (position == 1) {

                    executorService.execute(() -> {

                        tvshowsResponses = database.tvshowsDao().getAll();

                        handler.post(() -> {

                            favoriteTvshowsAdapter = new FavoriteTvshowsAdapter(tvshowsResponses);
                            recyclerView.setAdapter(favoriteTvshowsAdapter);
                        });
                    });
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }
}
