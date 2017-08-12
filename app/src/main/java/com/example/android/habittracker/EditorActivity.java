package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

/**
 * Created by tom.mills-mock on 12/08/2017.
 */

public class EditorActivity extends AppCompatActivity {

    // Set up of the fields to enter the database activity details
    private EditText mActivityName;
    private EditText mActivityDuration;
    private EditText mLocation;

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we need to read user input from
        // ToDo: add in the views from the layout once defined

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabitDbHelper(this);
    }

    private void insertHabit() {

        String nameString = mActivityName.getText().toString().trim();
        Integer durationInteger = Integer.parseInt(mActivityDuration.getText().toString().trim());
        Integer locationInteger = Integer.parseInt(mLocation.getText().toString().trim());

        // get the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values with Culomn names are the keys
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_ACTIVITY_NAME, nameString);
        values.put(HabitEntry.COLUMN_ACTIVITY_DURATION, durationInteger);
        values.put(HabitEntry.COLUMN_ACTIVITY_LOCATION, locationInteger);

        // Insert the new row, returning teh primary key value of the new row
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        Log.v("EditorActivity", "New row ID " + newRowId);

        if (newRowId == -1) {
            Toast.makeText(getApplication().getBaseContext(), "Error with saving habit", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplication().getBaseContext(), "Habit saved with ID: " + newRowId, Toast.LENGTH_LONG).show();
        }
    }


}
