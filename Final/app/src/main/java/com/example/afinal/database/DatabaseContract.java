package com.example.afinal.database;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static String TABLE_NAME = "content";

    public static final class ContentColumns implements BaseColumns {

        public static String TITLE = "title";
        public static String RELEASE_YEAR = "release_year";
        public static String CONTENT_TYPE = "content_type";
    }
}
