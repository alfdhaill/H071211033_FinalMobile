package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.afinal.database.ContentHelper;
import com.example.afinal.database.DatabaseContract;
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
    private ContentHelper contentHelper;

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

        contentHelper = ContentHelper.getInstance(getApplicationContext());
        contentHelper.open();

        mtvTitle = findViewById(R.id.mtv_title);
        mtvDate = findViewById(R.id.mtv_date);
        mtvSynopsis = findViewById(R.id.mtv_synopsis);
        mtvRating = findViewById(R.id.mtv_vote_average);
        ivBackdrop = findViewById(R.id.iv_backdrop);
        ivPoster = findViewById(R.id.iv_poster);
        ivContentType = findViewById(R.id.iv_content_type);
        checkBox = findViewById(R.id.cb);

        inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        outputFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());

        String type = getIntent().getStringExtra(EXTRA_TYPE);

        if (TYPE_MOVIE.equals(type)) {
            setupDetailsForMovie();
        } else if (TYPE_TV_SHOW.equals(type)) {
            setupDetailsForTvShow();
        }

        contentHelper.close();
    }

    private void setupDetailsForMovie() {
        MoviesResponse moviesResponse = getIntent().getParcelableExtra(TYPE_MOVIE);
        setMovieDetails(moviesResponse);

        checkBox.setChecked(isContentSaved(moviesResponse.getId()));

        checkBox.setOnClickListener(v -> {
            if (checkBox.isChecked()) {

                if (saveMovie(moviesResponse) > 0) {

                    showSnackbar(moviesResponse.getTitle() + " " + getString(R.string.is_added));
                } else {

                    showSnackbar(moviesResponse.getTitle() + " " + getString(R.string.failed_add));
                }
            } else {

                if (deleteContent(moviesResponse.getId()) > 0) {

                    showSnackbar(moviesResponse.getTitle() + " " + getString(R.string.is_removed));
                } else {

                    showSnackbar(moviesResponse.getTitle() + " " + getString(R.string.failed_remove));
                }
            }
        });
    }

    private void setupDetailsForTvShow() {
        TvshowsResponse tvshowsResponse = getIntent().getParcelableExtra(TYPE_TV_SHOW);
        setTvshowsDetails(tvshowsResponse);

        checkBox.setChecked(isContentSaved(tvshowsResponse.getId()));

        checkBox.setOnClickListener(v -> {
            if (checkBox.isChecked()) {

                if (saveTvshow(tvshowsResponse) > 0) {

                    showSnackbar(tvshowsResponse.getName() + " " + getString(R.string.is_added));
                } else {

                    showSnackbar(tvshowsResponse.getName() + " " + getString(R.string.failed_add));
                }
            } else {

                if (deleteContent(tvshowsResponse.getId()) > 0) {

                    showSnackbar(tvshowsResponse.getName() + " " + getString(R.string.is_removed));
                } else {

                    showSnackbar(tvshowsResponse.getName() + " " + getString(R.string.failed_remove));
                }
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

    private boolean isContentSaved(int id) {
        Cursor cursor = contentHelper.queryById(id);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    private long saveMovie(MoviesResponse response) {
        ContentValues values = getMoviesValues(response);

        return contentHelper.insert(values);
    }

    private long saveTvshow(TvshowsResponse response) {
        ContentValues values = getTvshowValues(response);

        return contentHelper.insert(values);
    }

    private long deleteContent(int id) {

        return contentHelper.deleteById(String.valueOf(id));
    }

    private ContentValues getMoviesValues(MoviesResponse response) {

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ContentColumns.ID, response.getId());
        values.put(DatabaseContract.ContentColumns.TITLE, response.getTitle());
        values.put(DatabaseContract.ContentColumns.VOTE_AVERAGE, response.getVoteAverage());
        values.put(DatabaseContract.ContentColumns.OVERVIEW, response.getSynopsis());
        values.put(DatabaseContract.ContentColumns.RELEASE_YEAR, response.getReleaseYear());
        values.put(DatabaseContract.ContentColumns.POSTER_PATH, response.getPosterPath());
        values.put(DatabaseContract.ContentColumns.BACKDROP_PATH, response.getBackdropPath());
        values.put(DatabaseContract.ContentColumns.CONTENT_TYPE, response.getContentType());
        return values;
    }

    private ContentValues getTvshowValues(TvshowsResponse response) {

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ContentColumns.ID, response.getId());
        values.put(DatabaseContract.ContentColumns.TITLE, response.getName());
        values.put(DatabaseContract.ContentColumns.VOTE_AVERAGE, response.getVoteAverage());
        values.put(DatabaseContract.ContentColumns.OVERVIEW, response.getSynopsis());
        values.put(DatabaseContract.ContentColumns.RELEASE_YEAR, response.getAirYear());
        values.put(DatabaseContract.ContentColumns.POSTER_PATH, response.getPosterPath());
        values.put(DatabaseContract.ContentColumns.BACKDROP_PATH, response.getBackdropPath());
        values.put(DatabaseContract.ContentColumns.CONTENT_TYPE, response.getContentType());
        return values;
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