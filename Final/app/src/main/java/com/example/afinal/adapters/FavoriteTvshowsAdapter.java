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
import com.example.afinal.models.TvshowsResponse;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class FavoriteTvshowsAdapter extends RecyclerView
        .Adapter<FavoriteTvshowsAdapter.FavoriteTvshowsViewHolder>{

    List<TvshowsResponse> tvshowsResponses;

    public FavoriteTvshowsAdapter(List<TvshowsResponse> tvshowsResponses) {
        this.tvshowsResponses = tvshowsResponses;
    }

    @NonNull
    @Override
    public FavoriteTvshowsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoriteTvshowsAdapter.FavoriteTvshowsViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_favorites, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteTvshowsViewHolder holder, int position) {

        TvshowsResponse tvshowsResponse = tvshowsResponses.get(position);

        String releaseYear = tvshowsResponse.getAirYear();
        String posterPath = tvshowsResponse.getPosterPath();
        String imageUrl = "https://image.tmdb.org/t/p/w500" + posterPath;


        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.ivPoster);

        holder.tvName.setText(tvshowsResponse.getName());

        if (releaseYear != null && releaseYear.length() >= 4) {
            String year = releaseYear.substring(0, 4);
            holder.tvAirDate.setText(year);
        }

        holder.materialCardView.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), DetailsActivity.class);

            intent.putExtra(DetailsActivity.TYPE_TV_SHOW, tvshowsResponse);
            intent.putExtra(DetailsActivity.EXTRA_TYPE, DetailsActivity.TYPE_TV_SHOW);

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tvshowsResponses.size();
    }

    public static class FavoriteTvshowsViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView materialCardView;
        ImageView ivPoster, ivContentType;
        TextView tvName, tvAirDate;

        public FavoriteTvshowsViewHolder(@NonNull View itemView) {
            super(itemView);

            materialCardView = itemView.findViewById(R.id.mcv_favorites);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            ivContentType = itemView.findViewById(R.id.iv_content_type);
            tvName = itemView.findViewById(R.id.tv_title);
            tvAirDate = itemView.findViewById(R.id.tv_year_date);
        }
    }
}
