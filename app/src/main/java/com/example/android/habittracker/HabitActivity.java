package com.example.android.habittracker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

public class HabitActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);

        mDbHelper = new HabitDbHelper(this);
    }

    public Cursor queryAllHabits() {
        // Create and/or ipen a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_ACTIVITY_NAME,
                HabitEntry.COLUMN_ACTIVITY_DURATION,
                HabitEntry.COLUMN_ACTIVITY_LOCATION,
        };

        Cursor cursor = db.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, null);
        return cursor;
    }

    private void displayDatabaseInto() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        HabitDbHelper mDbHelper = new HabitDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_ACTIVITY_NAME,
                HabitEntry.COLUMN_ACTIVITY_DURATION,
                HabitEntry.COLUMN_ACTIVITY_LOCATION,
        };

        Cursor cursor = db.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, null);

        TextView displayView = (TextView) findViewById(R.id.text_view_habit);

        try {
            displayView.setText("The habits table contains " + cursor.getCount() + " activities.\n\n");
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_ACTIVITY_NAME + " - " +
                    HabitEntry.COLUMN_ACTIVITY_DURATION + " - " +
                    HabitEntry.COLUMN_ACTIVITY_LOCATION + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int namecolumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_ACTIVITY_NAME);
            int durationColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_ACTIVITY_DURATION);
            int locationColumnindex = cursor.getColumnIndex(HabitEntry.COLUMN_ACTIVITY_LOCATION);

            // Iterate through all of teh returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that inde to extraact the values of the woek at the current row the cursor is on
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(namecolumnIndex);
                Integer currentDuration = cursor.getInt(durationColumnIndex);
                Integer currentLocation = cursor.getInt(locationColumnindex);

                String currentLocationText;
                if (currentLocation == 1) {
                    currentLocationText = "At Home";
                } else if (currentLocation == 2) {
                    currentLocationText = "At Work";
                } else if (currentLocation == 3) {
                    currentLocationText = "At the Gym";
                } else if (currentLocation == 4) {
                    currentLocationText = "Outside";
                } else currentLocationText = "Unknown";

                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentDuration + " - " +
                        currentLocationText));
            }
        } finally {
            cursor.close();
        }
    }
}
