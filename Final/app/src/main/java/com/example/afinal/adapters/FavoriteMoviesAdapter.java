package com.example.afinal.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.afinal.DetailsActivity;
import com.example.afinal.R;
import com.example.afinal.models.MoviesResponse;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class FavoriteMoviesAdapter extends RecyclerView
        .Adapter<FavoriteMoviesAdapter.FavoriteMoviesViewHolder>{

    List<MoviesResponse> moviesResponses;

    public FavoriteMoviesAdapter(List<MoviesResponse> moviesResponses) {
        this.moviesResponses = moviesResponses;
    }

    @NonNull
    @Override
    public FavoriteMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoriteMoviesViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_favorites, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMoviesViewHolder holder, int position) {

        MoviesResponse moviesResponse = moviesResponses.get(position);

        String releaseYear = moviesResponse.getReleaseYear();
        String posterPath = moviesResponse.getPosterPath();
        String imageUrl = "https://image.tmdb.org/t/p/w500" + posterPath;

        holder.tvTitle.setText(moviesResponse.getTitle());
        holder.ivContentType.setImageResource(moviesResponse.getContentType());

        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.ivPoster);

        if (releaseYear != null && releaseYear.length() >= 4) {
            String year = releaseYear.substring(0, 4);
            holder.tvYearDate.setText(year);
        }

        holder.materialCardView.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), DetailsActivity.class);

            intent.putExtra(DetailsActivity.TYPE_MOVIE, moviesResponse);
            intent.putExtra(DetailsActivity.EXTRA_TYPE, DetailsActivity.TYPE_MOVIE);

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return moviesResponses.size();
    }

    public static class FavoriteMoviesViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView materialCardView;
        ImageView ivPoster, ivContentType;
        TextView tvTitle, tvYearDate;

        public FavoriteMoviesViewHolder(@NonNull View itemView) {
            super(itemView);

            materialCardView = itemView.findViewById(R.id.mcv_favorites);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            ivContentType = itemView.findViewById(R.id.iv_content_type);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvYearDate = itemView.findViewById(R.id.tv_year_date);
        }
    }
}
