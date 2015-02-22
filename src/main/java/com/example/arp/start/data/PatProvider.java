package com.example.arp.start.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import com.example.arp.start.data.NoticeContract.NoticeEntry;
import com.example.arp.start.data.PatContract.PatEntry;

/**
 * Created by shreya on 17/02/15.
 */
public class PatProvider extends ContentProvider {
    public static final String LOG_TAG = "Notice pro";
/*
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private static final UriMatcher sUriMatcher1 = buildUriMatcher1();
*/
    private static final int addition = 100;
    private static final int addition1 = 101;

    private PatDBHelper mOpenHelper;


    private static final SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();
    private  static  UriMatcher sUriMatcher;
    static {
        Log.d(LOG_TAG," MATCHER uri 1"+sUriMatcher);

        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = PatContract.CONTENT_AUTHORITY;
        final String authority1 = NoticeContract.CONTENT_AUTHORITY1;
        Log.d(LOG_TAG," MATCHER uri 2"+sUriMatcher);

        sUriMatcher.addURI(PatContract.CONTENT_AUTHORITY, PatContract.PATH_PAT,addition);
        Log.d(LOG_TAG,"URI MATCHER 1"+sUriMatcher);
        sUriMatcher.addURI(NoticeContract.CONTENT_AUTHORITY1, NoticeContract.PATH_PAT1,addition1);
Log.d(LOG_TAG,"URI MATCHER 2"+sUriMatcher);

    }
      /*  uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "sampleuri1", SAMPLE1);
        uriMatcher.addURI(PROVIDER_NAME, "sampleuri1/#", SAMPLE1_ID);
        uriMatcher.addURI(PROVIDER_NAME, "sampleuri2", SAMPLE2);
        uriMatch
    private static UriMatcher buildUriMatcher() {
        // I know what you're thinking.  Why create a UriMatcher when you can use regular
        // expressions instead?  Because you're not crazy, that's why.

        // All paths added to the UriMatcher have a corresponding code to return when a match is
        // found.  The code passed into the constructor represents the code to return for the root
        // URI.  It's common to use NO_MATCH as the code for this case.
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = PatContract.CONTENT_AUTHORITY;


        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI(authority, PatContract.PATH_PAT,addition);

        return matcher;
    }
    private static UriMatcher buildUriMatcher1() {
        // I know what you're thinking.  Why create a UriMatcher when you can use regular
        // expressions instead?  Because you're not crazy, that's why.

        // All paths added to the UriMatcher have a corresponding code to return when a match is
        // found.  The code passed into the constructor represents the code to return for the root
        // URI.  It's common to use NO_MATCH as the code for this case.
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority1 = NoticeContract.CONTENT_AUTHORITY1;

        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI(authority1, NoticeContract.PATH_PAT1,addition1);

        return matcher;
    }*/
    @Override
    public boolean onCreate() {
        mOpenHelper=new PatDBHelper(getContext());

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        Cursor retCursor;
        Log.d(LOG_TAG,"in Pat provider");

        // Here's the switch statement that, given a URI, will determine what kind of request it is,
        // and query the database accordingly.

        switch (sUriMatcher.match(uri)) {
            // "weather/*/*"

            // "weather"
            case addition: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        PatContract.PatEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder

                );

            }
            break;
                case addition1: {
                    retCursor = mOpenHelper.getReadableDatabase().query(
                            NoticeEntry.TABLE_NAME1,
                            projection,
                            selection,
                            selectionArgs,
                            null,
                            null,
                            sortOrder
                    );
            }
            // "location/*"

break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }
    @Override
    public String getType(Uri uri) {

        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case addition: {
                return PatContract.PatEntry.CONTENT_TYPE;

            }

            case addition1: {
                return NoticeContract.NoticeEntry.CONTENT_TYPE1;

            }


            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

    }
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case addition: {
                long _id = db.insert(PatContract.PatEntry.TABLE_NAME, null, contentValues);
                if ( _id > 0 )
                    returnUri = PatContract.PatEntry.buildTableFromId(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case addition1: {
                long _id = db.insert(NoticeEntry.TABLE_NAME1, null, contentValues);
                if ( _id > 0 )
                    returnUri = NoticeEntry.buildTableFromId1(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = new PatDBHelper(getContext()).getWritableDatabase();


        final int match = sUriMatcher.match(uri);
        Log.d(LOG_TAG,"in Pat provider2"+sUriMatcher.toString());

        switch (match) {
            case addition: {
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(PatEntry.TABLE_NAME, null,value);
                        if (-1 != _id)
                        {
                            returnCount++;
                        }

                    }

                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                    db.close();
                }

                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;

            }

            case addition1: {
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(NoticeEntry.TABLE_NAME1, null,value);
                        if (-1 != _id)
                        {
                            returnCount++;
                        }

                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                    db.close();
                }

                getContext().getContentResolver().notifyChange(uri, null);
                Log.d(LOG_TAG,"Insertion complete");
                return returnCount;

            }


            default:
                return super.bulkInsert(uri, values);
        }


    }

}
