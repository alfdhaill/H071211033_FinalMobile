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
import com.example.afinal.models.TvshowsResponse;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class TvshowsAdapter extends RecyclerView.Adapter<TvshowsAdapter.TvshowsViewHolder> {

    private List<TvshowsResponse> tvshowsResponses;

    public TvshowsAdapter(List<TvshowsResponse> tvshowsResponses) {
        this.tvshowsResponses = tvshowsResponses;
    }

    public void updateTvshowsData(List<TvshowsResponse> newData) {

        this.tvshowsResponses = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvshowsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TvshowsAdapter.TvshowsViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_movies_tv_shows, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TvshowsViewHolder holder, int position) {

        TvshowsResponse tvshowsResponse = tvshowsResponses.get(position);

        String title = tvshowsResponse.getName();
        String releaseYear = tvshowsResponse.getAirYear();
        String posterPath = tvshowsResponse.getPosterPath();
        String imageUrl = "https://image.tmdb.org/t/p/w500" + posterPath;

        tvshowsResponse.setContentType(R.drawable.baseline_tv_24);

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

            intent.putExtra(DetailsActivity.TYPE_TV_SHOW, tvshowsResponse);
            intent.putExtra(DetailsActivity.EXTRA_TYPE, DetailsActivity.TYPE_TV_SHOW);

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tvshowsResponses.size();
    }

    public static class TvshowsViewHolder extends RecyclerView.ViewHolder{

        MaterialCardView materialCardView;
        ImageView imageView;
        TextView tvTitle, tvYearDate;

        public TvshowsViewHolder(@NonNull View itemView) {
            super(itemView);

            materialCardView = itemView.findViewById(R.id.mcv_movies_tv_shows);
            imageView = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvYearDate = itemView.findViewById(R.id.tv_year_date);
        }
    }
}
