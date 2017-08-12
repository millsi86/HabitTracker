package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by tom.mills-mock on 12/08/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    // Log tag for debugging
    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    //Name of the database file
    private static final String DATABASE_NAME = "habit.db";

    // Database version number
    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_HABIT_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_ACTIVITY_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_ACTIVITY_DURATION + " INTEGER NOT NULL"
                + HabitEntry.COLUMN_ACTIVITY_LOCATION + " INTEGER NOT NULL DEFAULT 0);";

        Log.v(LOG_TAG, SQL_CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
