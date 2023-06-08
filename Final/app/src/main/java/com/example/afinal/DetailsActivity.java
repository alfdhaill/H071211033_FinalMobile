package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.afinal.models.MoviesResponse;
import com.example.afinal.models.TvshowsResponse;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_TYPE = "extra_type";
    public static final String TYPE_MOVIE = "movie";
    public static final String TYPE_TV_SHOW = "tv_show";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }

        Date date;
        String type = getIntent().getStringExtra(EXTRA_TYPE);
        Handler handler = new Handler();

        LinearProgressIndicator lpi = findViewById(R.id.lpi);
        MaterialTextView mtvTitle = findViewById(R.id.mtv_title);
        MaterialTextView mtvDate = findViewById(R.id.mtv_date);
        MaterialTextView mtvSynopsis = findViewById(R.id.mtv_synopsis);
        MaterialTextView mtvRating = findViewById(R.id.mtv_vote_average);
        ImageView ivBackdrop = findViewById(R.id.iv_backdrop);
        ImageView ivPoster = findViewById(R.id.iv_poster);
        ImageView ivContentType = findViewById(R.id.iv_content_type);
        CheckBox checkBox = findViewById(R.id.cb);

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());

        if (TYPE_MOVIE.equals(type)) {

            MoviesResponse moviesResponse = getIntent().getParcelableExtra(TYPE_MOVIE);
            String posterUrl = "https://image.tmdb.org/t/p/w500" + moviesResponse.getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w500" + moviesResponse.getBackdropPath();
            try {
                date = inputFormat.parse(moviesResponse.getReleaseYear());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            String outputDateStr = outputFormat.format(date);

            handler.postDelayed(() -> {

                lpi.setVisibility(View.GONE);
                Glide.with(DetailsActivity.this)
                        .load(posterUrl)
                        .into(ivPoster);
                Glide.with(DetailsActivity.this)
                        .load(backdropUrl)
                        .into(ivBackdrop);

                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }, 1900);


            mtvTitle.setText(moviesResponse.getTitle());
            mtvDate.setText(outputDateStr);
            mtvSynopsis.setText(moviesResponse.getSynopsis());
            mtvRating.setText(moviesResponse.getVoteAverage().toString());
            Glide.with(this)
                    .load(moviesResponse.getContentType())
                    .into(ivContentType);

            checkBox.setOnClickListener(v -> {

                if (checkBox.isChecked()) {

                    Snackbar.make(findViewById(android.R.id.content),
                            moviesResponse.getTitle() + " " + getString(R.string.is_added),
                            Snackbar.LENGTH_SHORT).show();
                } else {

                    Snackbar.make(findViewById(android.R.id.content),
                            moviesResponse.getTitle() + " " + getString(R.string.is_removed),
                            Snackbar.LENGTH_SHORT).show();
                }
            });

        } else if (TYPE_TV_SHOW.equals(type)) {

            TvshowsResponse tvshowsResponse = getIntent().getParcelableExtra(TYPE_TV_SHOW);
            String posterUrl = "https://image.tmdb.org/t/p/w500" + tvshowsResponse.getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w500" + tvshowsResponse.getBackdropPath();
            try {
                date = inputFormat.parse(tvshowsResponse.getAirYear());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            String outputDateStr = outputFormat.format(date);

            handler.postDelayed(() -> {

                lpi.setVisibility(View.GONE);
                Glide.with(DetailsActivity.this)
                        .load(posterUrl)
                        .into(ivPoster);
                Glide.with(DetailsActivity.this)
                        .load(backdropUrl)
                        .into(ivBackdrop);

                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }, 1900);


            mtvTitle.setText(tvshowsResponse.getName());
            mtvDate.setText(outputDateStr);
            mtvSynopsis.setText(tvshowsResponse.getSynopsis());
            mtvRating.setText(tvshowsResponse.getVoteAverage().toString());
            ivContentType.setImageResource(tvshowsResponse.getContentType());
            Glide.with(this)
                    .load(tvshowsResponse.getContentType())
                    .into(ivContentType);

            checkBox.setOnClickListener(v -> {

                if (checkBox.isChecked()) {

                    Snackbar.make(findViewById(android.R.id.content),
                            tvshowsResponse.getName() + " " + getString(R.string.is_added),
                            Snackbar.LENGTH_SHORT).show();
                } else {

                    Snackbar.make(findViewById(android.R.id.content),
                            tvshowsResponse.getName() + " " + getString(R.string.is_removed),
                            Snackbar.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {

        finish();
        return true;
    }
}
