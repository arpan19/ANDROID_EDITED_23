package com.example.arp.start.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Defines table and column names for the weather database.
 */
public class PatContract {
    public static final String CONTENT_AUTHORITY="com.example.arp.start";

    public static final Uri BASE_CONTENT_URI=Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PAT = "PatTable";

    /* Inner class that defines the table contents of the location table */
    public static final class PatEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PAT).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_PAT;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_PAT;
        public static final String TABLE_NAME = "PatTable";

        // The location setting string is what will be sent to openweathermap
        // as the location query.

        public static final String COLUMN_SERIAL_NUMBER = "serial_number";

        // Human readable location string, provided by the API.  Because for styling,
        // "Mountain View" is more recognizable than 94043.
        public static final String COLUMN_COMPANY_NAME = "company_name";

        // In order to uniquely pinpoint the location on the map when we launch the
        // map intent, we store the latitude and longitude as returned by openweathermap.
        public static final String COLUMN_DAT = "dat";
        public static final String COLUMN_ELIGIBILITY_CRITERIA = "eligibility_criteria";
        public static final String COLUMN_BRANCH = "branches";
        public static final String COLUMN_SALARY = "salary";
        public static final String COLUMN_DEADLINE = "deadline";
        public static final String COLUMN_OTHER_INFO = "other_info";
        public static Uri buildTableFromId(long _id)
        {
            return ContentUris.withAppendedId(CONTENT_URI, _id);
        }
        public static String getDateFromUri(Uri uri)
        {
            return uri.getPathSegments().get(2);
        }

    }

}




    /* Inner class that defines the table contents of the weather table */
