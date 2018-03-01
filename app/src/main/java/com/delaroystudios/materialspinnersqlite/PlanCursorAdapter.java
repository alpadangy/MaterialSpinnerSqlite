package com.delaroystudios.materialspinnersqlite;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.delaroystudios.materialspinnersqlite.data.FragranceContract;


/**
 * Created by delaroy on 2/27/18.
 */

public class PlanCursorAdapter extends CursorAdapter {


    public PlanCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView titleTextView = (TextView) view.findViewById(R.id.planText);

        int nameColumnIndex = cursor.getColumnIndex(FragranceContract.FragranceEntry.COLUMN_NAME);
        int descColumnIndex = cursor.getColumnIndex(FragranceContract.FragranceEntry.COLUMN_DESCRIPTION);
        int imageColumnIndex = cursor.getColumnIndex(FragranceContract.FragranceEntry.COLUMN_IMAGE);
        int priceColumnIndex = cursor.getColumnIndex(FragranceContract.FragranceEntry.COLUMN_PRICE);
        int userratingColumnIndex = cursor.getColumnIndex(FragranceContract.FragranceEntry.COLUMN_USERRATING);

        String nameDesc = cursor.getString(nameColumnIndex);
        String descDays = cursor.getString(descColumnIndex);
        String image = cursor.getString(imageColumnIndex);
        String price = cursor.getString(priceColumnIndex);
        String userrating = cursor.getString(userratingColumnIndex);

        titleTextView.setText(nameDesc + " " + price + " " + "Rating - " + userrating);

    }
}

