package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.afinal.databases.AppDatabase;
import com.example.afinal.models.MoviesResponse;
import com.example.afinal.models.TvshowsResponse;
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
    private AppDatabase database;
    private MaterialTextView mtvTitle;
    private MaterialTextView mtvDate;
    private MaterialTextView mtvSynopsis;
    private MaterialTextView mtvRating;
    private ImageView ivBackdrop;
    private ImageView ivPoster;
    private ImageView ivContentType;
    private CheckBox checkBox;

    private SimpleDateFormat inputFormat;
    private SimpleDateFormat outputFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mtvTitle = findViewById(R.id.mtv_title);
        mtvDate = findViewById(R.id.mtv_date);
        mtvSynopsis = findViewById(R.id.mtv_synopsis);
        mtvRating = findViewById(R.id.mtv_vote_average);
        ivBackdrop = findViewById(R.id.iv_backdrop);
        ivPoster = findViewById(R.id.iv_poster);
        ivContentType = findViewById(R.id.iv_content_type);
        checkBox = findViewById(R.id.cb);

        database = AppDatabase.getInstance(this);

        inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        outputFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());

        String type = getIntent().getStringExtra(EXTRA_TYPE);

        if (TYPE_MOVIE.equals(type)) {
            setupDetailsForMovie();
        } else if (TYPE_TV_SHOW.equals(type)) {
            setupDetailsForTvShow();
        }
    }

    private void setupDetailsForMovie() {
        MoviesResponse moviesResponse = getIntent().getParcelableExtra(TYPE_MOVIE);
        setMovieDetails(moviesResponse);

        checkBox.setChecked(database.moviesDao().isMovieExists(moviesResponse.getId()));

        checkBox.setOnClickListener(v -> {
            if (checkBox.isChecked()) {

                database.moviesDao().insert(moviesResponse);

                showSnackbar(moviesResponse.getTitle() + " " + getString(R.string.is_added));
            } else {

                database.moviesDao().delete(moviesResponse);

                showSnackbar(moviesResponse.getTitle() + " " + getString(R.string.is_removed));
            }
        });
    }

    private void setupDetailsForTvShow() {
        TvshowsResponse tvshowsResponse = getIntent().getParcelableExtra(TYPE_TV_SHOW);
        setTvshowsDetails(tvshowsResponse);

        checkBox.setChecked(database.tvshowsDao().isTvshowExists(tvshowsResponse.getId()));

        checkBox.setOnClickListener(v -> {
            if (checkBox.isChecked()) {

                database.tvshowsDao().insert(tvshowsResponse);

                showSnackbar(tvshowsResponse.getName() + " " + getString(R.string.is_added));
            } else {

                database.tvshowsDao().delete(tvshowsResponse);

                showSnackbar(tvshowsResponse.getName() + " " + getString(R.string.is_removed));
            }
        });
    }

    private void setMovieDetails(MoviesResponse response) {
        String posterUrl = "https://image.tmdb.org/t/p/w500" + response.getPosterPath();
        String backdropUrl = "https://image.tmdb.org/t/p/w500" + response.getBackdropPath();
        Date date = parseDate(response.getReleaseYear());

        mtvTitle.setText(response.getTitle());
        mtvDate.setText(formatDate(date));
        mtvSynopsis.setText(response.getSynopsis());
        mtvRating.setText(response.getVoteAverage().toString());
        loadImage(posterUrl, ivPoster);
        loadImage(backdropUrl, ivBackdrop);
        loadImage(response.getContentType(), ivContentType);
    }

    private void setTvshowsDetails(TvshowsResponse response) {
        String posterUrl = "https://image.tmdb.org/t/p/w500" + response.getPosterPath();
        String backdropUrl = "https://image.tmdb.org/t/p/w500" + response.getBackdropPath();
        Date date = parseDate(response.getAirYear());

        mtvTitle.setText(response.getName());
        mtvDate.setText(formatDate(date));
        mtvSynopsis.setText(response.getSynopsis());
        mtvRating.setText(response.getVoteAverage().toString());
        loadImage(posterUrl, ivPoster);
        loadImage(backdropUrl, ivBackdrop);
        loadImage(response.getContentType(), ivContentType);
    }

    private Date parseDate(String dateString) {
        try {
            return inputFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String formatDate(Date date) {
        return outputFormat.format(date);
    }

    private void loadImage(String imageUrl, ImageView imageView) {
        Glide.with(this)
                .load(imageUrl)
                .into(imageView);
    }

    private void loadImage(int resourceId, ImageView imageView) {
        Glide.with(this)
                .load(resourceId)
                .into(imageView);
    }

    private void showSnackbar(String message) {

        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {

        finish();
        return true;
    }
}