/*
package com.example.arp.start.data;
 */

/*
/*
 * Manages a local database for weather data.

public class NoticeDBHelper extends SQLiteOpenHelper {
String LOG="sql log";
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION2 = 2;

    private static final String DATABASE_NAME2 = "notice";

    public NoticeDBHelper(Context context) {
        super(context, DATABASE_NAME2, null, DATABASE_VERSION2);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a table to hold locations.  A location consists of the string supplied in the
        // location setting, the city name, and the latitude and longitude

        // TBD
        Log.d(LOG,"created db");
        final String SQL_CREATE_NOTICE_TABLE = "CREATE TABLE IF NOT EXISTS " + NoticeEntry.TABLE_NAME1 + " (" +
                // Why AutoIncrement here, and not above?
                // Unique keys will be auto-generated in either case.  But for weather
                // forecasting, it's reasonable to assume the user will want information
                // for a certain date and all dates *following*, so the forecast data
                // should be sorted accordingly.
                NoticeEntry._ID + " INTEGER PRIMARY KEY ," +
                NoticeEntry.COLUMN_SERIAL_NUMBER + " TEXT NOT NULL, " +
                // the ID of the location entry associated with this weather data
                NoticeEntry.COLUMN_DATE + " TEXT NOT NULL, " +
                NoticeEntry.COLUMN_NOTICE + " TEXT NOT NULL);";
        sqLiteDatabase.execSQL(SQL_CREATE_NOTICE_TABLE);
        Log.d(LOG,"created table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(("DROP TABLE IF EXISTS"+ NoticeEntry.TABLE_NAME1));
        onCreate(sqLiteDatabase);
    }
}  */