package com.example.afinal.database;

import android.database.Cursor;

import com.example.afinal.movies.MoviesResponse;
import com.example.afinal.tvshows.TvshowsResponse;

import java.util.ArrayList;
import java.util.List;

public class MappingHelper {

    public static List<MoviesResponse> mapCursorToMoviesList(Cursor cursor) {

        List<MoviesResponse> moviesResponses = new ArrayList<>();

        while (cursor.moveToNext()) {

            int dbId =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ContentColumns._ID));
            String title =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ContentColumns.TITLE));
            String releaseYear =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ContentColumns.RELEASE_YEAR));
            int contentType =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ContentColumns.CONTENT_TYPE));

            moviesResponses.add(new MoviesResponse(dbId, title, releaseYear, contentType));
        }

        return moviesResponses;
    }

    public static List<TvshowsResponse> mapCursorToTvshowsList(Cursor cursor) {

        List<TvshowsResponse> tvshowsResponses = new ArrayList<>();

        while (cursor.moveToNext()) {

            int dbId =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ContentColumns._ID));
            String title =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ContentColumns.TITLE));
            String releaseYear =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ContentColumns.RELEASE_YEAR));
            int contentType =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ContentColumns.CONTENT_TYPE));

            tvshowsResponses.add(new TvshowsResponse(dbId, title, releaseYear, contentType));
        }

        return tvshowsResponses;
    }
}
