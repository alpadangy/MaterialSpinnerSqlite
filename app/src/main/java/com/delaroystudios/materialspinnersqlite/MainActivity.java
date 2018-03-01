package com.delaroystudios.materialspinnersqlite;

import android.content.ContentUris;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.delaroystudios.materialspinnersqlite.data.FragranceContract;
import com.delaroystudios.materialspinnersqlite.data.FragranceDbHelper;

import fr.ganfra.materialspinner.MaterialSpinner;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    MaterialSpinner spinner;
    PlanCursorAdapter planCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragranceDbHelper dbHelper = new FragranceDbHelper(this);

        getSupportLoaderManager().initLoader(0, null, this);

        planCursorAdapter = new PlanCursorAdapter(this, null);


    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] PROJECTION = new String[]{
                FragranceContract.FragranceEntry._ID,
                        FragranceContract.FragranceEntry.COLUMN_NAME,
                        FragranceContract.FragranceEntry.COLUMN_DESCRIPTION,
                        FragranceContract.FragranceEntry.COLUMN_IMAGE,
                        FragranceContract.FragranceEntry.COLUMN_PRICE,
                        FragranceContract.FragranceEntry.COLUMN_USERRATING,



        };

        return new CursorLoader(this,   // Parent activity context
               FragranceContract.FragranceEntry.CONTENT_URI,   // Provider content URI to query
                PROJECTION,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        planCursorAdapter.swapCursor(data);

        spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setAdapter(planCursorAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //position = i;
                //currentPlanUri = ContentUris.withAppendedId(AppContract.AppEntry.CONTENT_URI_PLAN, i + 1);
            }

            @Override

            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        planCursorAdapter.swapCursor(null);
    }
}
