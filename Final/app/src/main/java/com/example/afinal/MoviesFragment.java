package com.example.afinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import com.example.afinal.adapters.MoviesAdapter;
import com.example.afinal.apis.ApiConfig;
import com.example.afinal.models.MoviesResponse;
import com.example.afinal.responses.ListMoviesResponse;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFragment extends Fragment {

    private MoviesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        activity.getSupportActionBar().show();
        activity.getSupportActionBar().setTitle(getString(R.string.now_playing));

        Call<ListMoviesResponse> call = ApiConfig.getApiService()
                .getMovies(MainActivity.language, 1);
        final List<MoviesResponse> moviesResponses = new ArrayList<>();

        LinearProgressIndicator linearProgressIndicator = view.findViewById(R.id.lpi);
        MaterialTextView mtvNoInternet = view.findViewById(R.id.mtv_no_internet);
        RecyclerView recyclerView = view.findViewById(R.id.rv_movies);
        Button btnRetry = view.findViewById(R.id.btn_retry);
        MaterialButtonToggleGroup materialButtonToggleGroup = view.findViewById(R.id.toggleButton);

        materialButtonToggleGroup.check(R.id.button2);
        btnRetry.setOnClickListener(v -> requireActivity().recreate());

        call.enqueue(new Callback<ListMoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<ListMoviesResponse> call,
                                   @NonNull Response<ListMoviesResponse> response) {

                linearProgressIndicator.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                materialButtonToggleGroup.setVisibility(View.VISIBLE);

                if (response.isSuccessful() && response.body() != null) {

                    ListMoviesResponse listMoviesResponse = response.body();
                    moviesResponses.addAll(listMoviesResponse.getMoviesData());
                }

                List<MoviesResponse> defaultMoviesResponses = new ArrayList<>(moviesResponses);
                adapter = new MoviesAdapter(moviesResponses);

                materialButtonToggleGroup.addOnButtonCheckedListener
                        ((group, checkedId, isChecked) -> {

                    if (isChecked) {

                        if (checkedId == R.id.button1) {

                            // Sort titles in ascending order
                            moviesResponses.sort(Comparator.comparing(MoviesResponse::getTitle));
                        } else if (checkedId == R.id.button2) {

                            // Set to default order
                            moviesResponses.clear();
                            moviesResponses.addAll(defaultMoviesResponses);
                        } else if (checkedId == R.id.button3) {

                            // Sort titles in descending order
                            moviesResponses.sort((o1, o2) -> o2.getTitle()
                                    .compareTo(o1.getTitle()));
                        }

                        adapter.updateMoviesData(moviesResponses);
                    }
                });

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager
                        (2, LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<ListMoviesResponse> call, @NonNull Throwable t) {
                mtvNoInternet.setVisibility(View.VISIBLE);
                btnRetry.setVisibility(View.VISIBLE);
            }
        });
    }
}


//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.search_menu, menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        searchView.setQueryHint("Search now playing movies");
//
//        // Set the query listener
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // Handle query submission
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // Handle query text change
//                return true;
//            }
//        });
//
//        super.onCreateOptionsMenu(menu, inflater);
//    }
