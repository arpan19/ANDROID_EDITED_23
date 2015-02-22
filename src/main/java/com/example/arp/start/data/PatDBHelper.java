package com.example.arp.start.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.arp.start.data.NoticeContract.NoticeEntry;
import com.example.arp.start.data.PatContract.PatEntry;


/**
 * Manages a local database for weather data.
 */
public class PatDBHelper extends SQLiteOpenHelper {
public static final String LOG="helper";
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "pat1";

    public PatDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a table to hold locations.  A location consists of the string supplied in the
        // location setting, the city name, and the latitude and longitude

        // TBD

        final String SQL_CREATE_WEATHER_TABLE = "CREATE TABLE IF NOT EXISTS " + PatEntry.TABLE_NAME + " (" +
                // Why AutoIncrement here, and not above?
                // Unique keys will be auto-generated in either case.  But for weather
                // forecasting, it's reasonable to assume the user will want information
                // for a certain date and all dates *following*, so the forecast data
                // should be sorted accordingly.
                PatContract.PatEntry._ID + " INTEGER PRIMARY KEY ," +
                PatContract.PatEntry.COLUMN_SERIAL_NUMBER + " TEXT NOT NULL, " +
                // the ID of the location entry associated with this weather data
                PatContract.PatEntry.COLUMN_COMPANY_NAME + " TEXT NOT NULL, " +
                PatContract.PatEntry.COLUMN_DAT + " TEXT NOT NULL, " +
                PatContract.PatEntry.COLUMN_ELIGIBILITY_CRITERIA + " TEXT NOT NULL, " +
                PatContract.PatEntry.COLUMN_BRANCH + " TEXT NOT NULL," +

                PatContract.PatEntry.COLUMN_SALARY + " TEXT NOT NULL, " +
                PatContract.PatEntry.COLUMN_DEADLINE + " TEXT NOT NULL, " +

                PatContract.PatEntry.COLUMN_OTHER_INFO + " TEXT NOT NULL );";

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

        sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_NOTICE_TABLE);
        Log.d(LOG,"created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
sqLiteDatabase.execSQL(("DROP TABLE IF EXISTS"+ PatContract.PatEntry.TABLE_NAME));

        sqLiteDatabase.execSQL(("DROP TABLE IF EXISTS"+ NoticeEntry.TABLE_NAME1));
        onCreate(sqLiteDatabase);


    }
}