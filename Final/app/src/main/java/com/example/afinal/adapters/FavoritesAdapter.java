package com.example.afinal.adapters;

import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;
import com.example.afinal.models.MoviesResponse;
import com.example.afinal.models.TvshowsResponse;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>{

    private List<MoviesResponse> moviesResponseList;
    private List<TvshowsResponse> tvshowsResponseList;

    public FavoritesAdapter(List<MoviesResponse> moviesResponseList, List<TvshowsResponse> tvshowsResponseList) {
        this.moviesResponseList = moviesResponseList;
        this.tvshowsResponseList = tvshowsResponseList;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class FavoritesViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvYearDate;
        ImageView ivPoster, ivContentType;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvYearDate = itemView.findViewById(R.id.tv_year_date);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            ivContentType = itemView.findViewById(R.id.iv_content_type);
        }
    }
}
