package com.delaroystudios.materialspinnersqlite.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by delaroy on 9/3/17.
 */

public class FragranceProvider extends ContentProvider {

    public static final String LOG_TAG = FragranceProvider.class.getSimpleName();

    /** URI matcher code for the content URI for the fragrance table */
    private static final int FRAGRANCES = 100;

    /** URI matcher code for the content URI for a single fragrance in the fragrance table */
    private static final int FRAGRANCE_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
         sUriMatcher.addURI(FragranceContract.CONTENT_AUTHORITY, FragranceContract.PATH_FRAGRANCE, FRAGRANCES);


        sUriMatcher.addURI(FragranceContract.CONTENT_AUTHORITY, FragranceContract.PATH_FRAGRANCE + "/#", FRAGRANCE_ID);

    }

    /** Database helper object */
    private FragranceDbHelper fragranceDbHelper;

    @Override
    public boolean onCreate() {
        fragranceDbHelper = new FragranceDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        // Get readable database
        SQLiteDatabase database = fragranceDbHelper.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);
        switch (match) {
            case FRAGRANCES:
                // For the fragrance code, query the fragrance table directly with the given
                // projection, selection, selection arguments, and sort order. The cursor
                // could contain multiple rows of the fragrance table.
                cursor = database.query(FragranceContract.FragranceEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case FRAGRANCE_ID:

                selection = FragranceContract.FragranceEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                // This will perform a query on the fragrance table where the _id equals 3 to return a
                // Cursor containing that row of the table.
                cursor = database.query(FragranceContract.FragranceEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        // Return the cursor
        return cursor;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case FRAGRANCES:
                return insertFragrance(uri, contentValues);

            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }


    private Uri insertFragrance(Uri uri, ContentValues values) {

        // Get writeable database
        SQLiteDatabase database = fragranceDbHelper.getWritableDatabase();

        // Insert the new cart with the given values
        long id = database.insert(FragranceContract.FragranceEntry.TABLE_NAME, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Notify all listeners that the data has changed for the fragrance content URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        // Get access to the database and write URI matching code to recognize a single item

        // Return the number of tasks deleted
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
