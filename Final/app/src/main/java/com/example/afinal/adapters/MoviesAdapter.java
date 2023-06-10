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

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>{

    private List<MoviesResponse> moviesResponses;

    public MoviesAdapter(List<MoviesResponse> moviesResponses) {

        this.moviesResponses = moviesResponses;
    }

    public void updateMoviesData(List<MoviesResponse> newData) {

        this.moviesResponses = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoviesViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_movies_tv_shows, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {

        MoviesResponse moviesResponse = moviesResponses.get(position);

        String title = moviesResponse.getTitle();
        String releaseYear = moviesResponse.getReleaseYear();
        String posterPath = moviesResponse.getPosterPath();
        String imageUrl = "https://image.tmdb.org/t/p/w500" + posterPath;

        moviesResponse.setContentType(R.drawable.baseline_movie_24);

        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.imageView);

        if (title != null && title.length() > 18) {
            title = title.substring(0, 18) + "...";
        }
        holder.tvTitle.setText(title);

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

    public static class MoviesViewHolder extends RecyclerView.ViewHolder{

        MaterialCardView materialCardView;
        ImageView imageView;
        TextView tvTitle, tvYearDate;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);

            materialCardView = itemView.findViewById(R.id.mcv_movies_tv_shows);
            imageView = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvYearDate = itemView.findViewById(R.id.tv_year_date);
        }
    }
}
