package com.example.afinal.database;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static String TABLE_NAME = "content";

    public static final class ContentColumns implements BaseColumns {

        public static String ID = "id";
        public static String TITLE = "title";
        public static String VOTE_AVERAGE = "vote_average";
        public static String OVERVIEW = "overview";
        public static String RELEASE_YEAR = "release_year";
        public static String POSTER_PATH = "poster_path";
        public static String BACKDROP_PATH = "backdrop_path";
        public static String CONTENT_TYPE = "content_type";
    }
}
