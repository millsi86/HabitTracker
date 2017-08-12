package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by tom.mills-mock on 12/08/2017.
 */

public class HabitContract {

    private HabitContract() {
    }

    public static final class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habit";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_ACTIVITY_NAME = "Activity";
        public static final String COLUMN_ACTIVITY_DURATION = "Duration";
        public static final String COLUMN_ACTIVITY_LOCATION = "Location";

        /**
         * Possible Values for Location
         */
        public static final int LOCATION_UNKNOWN = 0;
        public static final int LOCATION_AT_HOME = 1;
        public static final int LOCATION_AT_WORK = 2;
        public static final int LOCATION_AT_GYM = 3;
        public static final int LOCATION_OUTSIDE = 4;
    }
}
