package com.example.afinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.concurrent.ExecutorService;

public class FavoritesFragment extends Fragment {

    Handler handler;
    ExecutorService executorService;

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

        handler = new Handler();
        LinearProgressIndicator lpi = view.findViewById(R.id.lpi);
        RecyclerView recyclerView = view.findViewById(R.id.rv_favorites);

        recyclerView.setVisibility(View.INVISIBLE);
        handler.postDelayed(()->{

            recyclerView.setVisibility(View.VISIBLE);
        }, 1100);
    }
}