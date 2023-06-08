package com.example.afinal.database;

import android.database.Cursor;

import com.example.afinal.models.MoviesResponse;
import com.example.afinal.models.TvshowsResponse;

import java.util.ArrayList;
import java.util.List;

public class MappingHelper {

    public static List<MoviesResponse> mapCursorToMoviesList(Cursor cursor) {

        List<MoviesResponse> moviesResponses = new ArrayList<>();

        while (cursor.moveToNext()) {

            int id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(
                            DatabaseContract.ContentColumns._ID));

            String title =
                    cursor.getString(cursor.getColumnIndexOrThrow(
                            DatabaseContract.ContentColumns.TITLE));

            Float voteAverage =
                    cursor.getFloat(cursor.getColumnIndexOrThrow(
                            DatabaseContract.ContentColumns.VOTE_AVERAGE));

            String synopsis =
                    cursor.getString(cursor.getColumnIndexOrThrow(
                            DatabaseContract.ContentColumns.OVERVIEW));

            String releaseYear =
                    cursor.getString(cursor.getColumnIndexOrThrow(
                            DatabaseContract.ContentColumns.RELEASE_YEAR));

            String posterPath =
                    cursor.getString(cursor.getColumnIndexOrThrow(
                            DatabaseContract.ContentColumns.POSTER_PATH));

            String backdropPath =
                    cursor.getString(cursor.getColumnIndexOrThrow(
                            DatabaseContract.ContentColumns.BACKDROP_PATH));

            int contentType =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ContentColumns.CONTENT_TYPE));

            moviesResponses.add(new MoviesResponse(id, title, voteAverage, synopsis, releaseYear,
                    posterPath, backdropPath, contentType));
        }

        return moviesResponses;
    }

    public static List<TvshowsResponse> mapCursorToTvshowsList(Cursor cursor) {

        List<TvshowsResponse> tvshowsResponses = new ArrayList<>();

        while (cursor.moveToNext()) {

            int id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(
                            DatabaseContract.ContentColumns._ID));

            String name =
                    cursor.getString(cursor.getColumnIndexOrThrow(
                            DatabaseContract.ContentColumns.TITLE));

            Float voteAverage =
                    cursor.getFloat(cursor.getColumnIndexOrThrow(
                            DatabaseContract.ContentColumns.VOTE_AVERAGE));

            String synopsis =
                    cursor.getString(cursor.getColumnIndexOrThrow(
                            DatabaseContract.ContentColumns.OVERVIEW));

            String airYear =
                    cursor.getString(cursor.getColumnIndexOrThrow(
                            DatabaseContract.ContentColumns.RELEASE_YEAR));

            String posterPath =
                    cursor.getString(cursor.getColumnIndexOrThrow(
                            DatabaseContract.ContentColumns.POSTER_PATH));

            String backdropPath =
                    cursor.getString(cursor.getColumnIndexOrThrow(
                            DatabaseContract.ContentColumns.BACKDROP_PATH));

            int contentType =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ContentColumns.CONTENT_TYPE));

            tvshowsResponses.add(new TvshowsResponse(id, name, voteAverage, synopsis, airYear,
                    posterPath, backdropPath, contentType));
        }

        return tvshowsResponses;
    }
}
