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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.afinal.adapters.TvshowsAdapter;
import com.example.afinal.apis.ApiConfig;
import com.example.afinal.models.TvshowsResponse;
import com.example.afinal.responses.ListTvshowsResponse;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvshowsFragment extends Fragment {

    TvshowsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_t_v_shows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        activity.getSupportActionBar().show();
        activity.getSupportActionBar().setTitle(getString(R.string.top_rated));

        Call<ListTvshowsResponse> call = ApiConfig.getApiService().
                getTvshows(MainActivity.language, 1);
        final List<TvshowsResponse> tvshowsResponses = new ArrayList<>();

        LinearProgressIndicator linearProgressIndicator = view.findViewById(R.id.lpi);
        MaterialTextView mtvNoInternet = view.findViewById(R.id.mtv_no_internet);
        RecyclerView recyclerView = view.findViewById(R.id.rv_tv_shows);
        Button btnRetry = view.findViewById(R.id.btn_retry);
        MaterialButtonToggleGroup materialButtonToggleGroup = view.findViewById(R.id.toggleButton);

        materialButtonToggleGroup.check(R.id.button2);
        btnRetry.setOnClickListener(v -> requireActivity().recreate());

        call.enqueue(new Callback<ListTvshowsResponse>() {
            @Override
            public void onResponse(@NonNull Call<ListTvshowsResponse> call,
                                   @NonNull Response<ListTvshowsResponse> response) {

                linearProgressIndicator.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                materialButtonToggleGroup.setVisibility(View.VISIBLE);

                if (response.isSuccessful() && response.body() != null) {

                    ListTvshowsResponse listTvshowsResponse = response.body();
                    tvshowsResponses.addAll(listTvshowsResponse.getTvshowsData());
                }

                List<TvshowsResponse> defaultTvshowsResponses = new ArrayList<>(tvshowsResponses);
                adapter = new TvshowsAdapter(tvshowsResponses);

                materialButtonToggleGroup.addOnButtonCheckedListener
                        ((group, checkedId, isChecked) -> {

                            if (isChecked) {

                                if (checkedId == R.id.button1) {

                                    // Sort titles in ascending order
                                    tvshowsResponses.sort(Comparator
                                            .comparing(TvshowsResponse::getName));
                                } else if (checkedId == R.id.button2) {

                                    // Set to default order
                                    tvshowsResponses.clear();
                                    tvshowsResponses.addAll(defaultTvshowsResponses);
                                } else if (checkedId == R.id.button3) {

                                    // Sort titles in descending order
                                    tvshowsResponses.sort((o1, o2) -> o2.getName()
                                            .compareTo(o1.getName()));
                                }

                                adapter.updateTvshowsData(tvshowsResponses);
                            }
                        });

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager
                        (2, LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<ListTvshowsResponse> call, @NonNull Throwable t) {

                mtvNoInternet.setVisibility(View.VISIBLE);
                btnRetry.setVisibility(View.VISIBLE);
            }
        });

    }
}