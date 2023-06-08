package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.afinal.database.ContentHelper;
import com.example.afinal.database.DatabaseContract;
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
    private ContentHelper contentHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Date date;
        String type = getIntent().getStringExtra(EXTRA_TYPE);
        contentHelper = ContentHelper.getInstance(getApplicationContext());
        contentHelper.open();

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
            assert date != null;
            String outputDateStr = outputFormat.format(date);

                lpi.setVisibility(View.GONE);
                Glide.with(DetailsActivity.this)
                        .load(posterUrl)
                        .into(ivPoster);
                Glide.with(DetailsActivity.this)
                        .load(backdropUrl)
                        .into(ivBackdrop);



            mtvTitle.setText(moviesResponse.getTitle());
            mtvDate.setText(outputDateStr);
            mtvSynopsis.setText(moviesResponse.getSynopsis());
            mtvRating.setText(moviesResponse.getVoteAverage().toString());
            Glide.with(this)
                    .load(moviesResponse.getContentType())
                    .into(ivContentType);

            Cursor cursor = contentHelper.queryById(moviesResponse.getId());

            checkBox.setChecked(cursor.getCount() > 0);
            cursor.close();

            checkBox.setOnClickListener(v -> {

                if (checkBox.isChecked()) {

                    int id = moviesResponse.getId();
                    String title = moviesResponse.getTitle();
                    Float voteAverage = moviesResponse.getVoteAverage();
                    String synopsis = moviesResponse.getSynopsis();
                    String releaseYear = moviesResponse.getReleaseYear();
                    String posterPath = moviesResponse.getPosterPath();
                    String backdropPath = moviesResponse.getBackdropPath();
                    int contentType = moviesResponse.getContentType();
                    ContentValues values = new ContentValues();

                    values.put(DatabaseContract.ContentColumns.ID, id);
                    values.put(DatabaseContract.ContentColumns.TITLE, title);
                    values.put(DatabaseContract.ContentColumns.VOTE_AVERAGE, voteAverage);
                    values.put(DatabaseContract.ContentColumns.OVERVIEW, synopsis);
                    values.put(DatabaseContract.ContentColumns.RELEASE_YEAR, releaseYear);
                    values.put(DatabaseContract.ContentColumns.POSTER_PATH, posterPath);
                    values.put(DatabaseContract.ContentColumns.BACKDROP_PATH, backdropPath);
                    values.put(DatabaseContract.ContentColumns.CONTENT_TYPE, contentType);

                    contentHelper.insert(values);

                    Snackbar.make(findViewById(android.R.id.content),
                            moviesResponse.getTitle() + " " + getString(R.string.is_added),
                            Snackbar.LENGTH_SHORT).show();
                } else {

                    contentHelper.deleteById(String.valueOf(moviesResponse.getId()));

                    Snackbar.make(findViewById(android.R.id.content),
                            moviesResponse.getTitle() + " " + getString(R.string.is_removed),
                            Snackbar.LENGTH_SHORT).show();
                }
            });

        } else if (TYPE_TV_SHOW.equals(type)) {

            TvshowsResponse tvshowsResponse = getIntent().getParcelableExtra(TYPE_TV_SHOW);
            Cursor cursor = contentHelper.queryById(tvshowsResponse.getId());
            String posterUrl = "https://image.tmdb.org/t/p/w500" + tvshowsResponse.getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w500" + tvshowsResponse.getBackdropPath();
            try {
                date = inputFormat.parse(tvshowsResponse.getAirYear());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            assert date != null;
            String outputDateStr = outputFormat.format(date);

            checkBox.setChecked(cursor.getCount() > 0);

            cursor.close();

                Glide.with(DetailsActivity.this)
                        .load(posterUrl)
                        .into(ivPoster);
                Glide.with(DetailsActivity.this)
                        .load(backdropUrl)
                        .into(ivBackdrop);



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

                    int id = tvshowsResponse.getId();
                    String name = tvshowsResponse.getName();
                    Float voteAverage = tvshowsResponse.getVoteAverage();
                    String synopsis = tvshowsResponse.getSynopsis();
                    String firstAirYear = tvshowsResponse.getAirYear();
                    String posterPath = tvshowsResponse.getPosterPath();
                    String backdropPath = tvshowsResponse.getBackdropPath();
                    int contentType = tvshowsResponse.getContentType();
                    ContentValues values = new ContentValues();

                    values.put(DatabaseContract.ContentColumns.ID, id);
                    values.put(DatabaseContract.ContentColumns.TITLE, name);
                    values.put(DatabaseContract.ContentColumns.VOTE_AVERAGE, voteAverage);
                    values.put(DatabaseContract.ContentColumns.OVERVIEW, synopsis);
                    values.put(DatabaseContract.ContentColumns.RELEASE_YEAR, firstAirYear);
                    values.put(DatabaseContract.ContentColumns.POSTER_PATH, posterPath);
                    values.put(DatabaseContract.ContentColumns.BACKDROP_PATH, backdropPath);
                    values.put(DatabaseContract.ContentColumns.CONTENT_TYPE, contentType);

                    contentHelper.insert(values);

                    Snackbar.make(findViewById(android.R.id.content),
                            tvshowsResponse.getName() + " " + getString(R.string.is_added),
                            Snackbar.LENGTH_SHORT).show();
                } else {

                    contentHelper.deleteById(String.valueOf(tvshowsResponse.getId()));

                    Snackbar.make(findViewById(android.R.id.content),
                            tvshowsResponse.getName() + " " + getString(R.string.is_removed),
                            Snackbar.LENGTH_SHORT).show();
                }
            });
        }

        contentHelper.close();

    }

    @Override
    public boolean onSupportNavigateUp() {

        finish();
        return true;
    }
}
