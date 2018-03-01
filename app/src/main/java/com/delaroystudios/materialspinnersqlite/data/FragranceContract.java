package com.delaroystudios.materialspinnersqlite.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by delaroy on 9/3/17.
 */

public class FragranceContract {

    private FragranceContract() {}

    public static final String CONTENT_AUTHORITY = "com.delaroystudios.materialspinnersqlite";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final String PATH_FRAGRANCE = "fragrance-path";



    public static final class FragranceEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_FRAGRANCE);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FRAGRANCE;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FRAGRANCE;

        /** Name of database table for fragrance */
        public final static String TABLE_NAME = "fragrances";


        public final static String _ID = BaseColumns._ID;


        /**
         * Name of the fragrance.
         *
         * Type: TEXT
         */
        public final static String COLUMN_NAME = "fragrancename";
        public final static String COLUMN_DESCRIPTION = "description";
        public final static String COLUMN_IMAGE = "imageurl";
        public final static String COLUMN_PRICE = "price";
        public final static String COLUMN_USERRATING = "userrating";
        public final static String COLUMN_ITEMID = "itemid";


    }

}

