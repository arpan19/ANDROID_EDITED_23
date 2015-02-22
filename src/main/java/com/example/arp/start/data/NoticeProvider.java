/*
package com.example.arp.start.data;
 */

/**
 * Created by shreya on 17/02/15.

public class NoticeProvider extends ContentProvider {
    public static final String LOG_TAG = "Notice pro";
    private static final UriMatcher sUriMatcher1 = buildUriMatcher1();
    private static final int addition1 = 100;
    private NoticeDBHelper mOpenHelper;
    private static final SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();

    private static UriMatcher buildUriMatcher1() {
        // I know what you're thinking.  Why create a UriMatcher when you can use regular
        // expressions instead?  Because you're not crazy, that's why.

        // All paths added to the UriMatcher have a corresponding code to return when a match is
        // found.  The code passed into the constructor represents the code to return for the root
        // URI.  It's common to use NO_MATCH as the code for this case.
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = NoticeContract.CONTENT_AUTHORITY1;

        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI(authority, NoticeContract.PATH_PAT1,addition1);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper=new NoticeDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // Here's the switch statement that, given a URI, will determine what kind of request it is,
        // and query the database accordingly.
        Cursor retCursor;
        Log.d(LOG_TAG, "uri query: " + uri);
        Log.d(LOG_TAG,"in notice provider");
        switch (sUriMatcher1.match(uri)) {
            // "weather/*"

            // "weather"
            case addition1: {
                Log.d(LOG_TAG,"this query: "+uri);

                retCursor = mOpenHelper.getReadableDatabase().query(
                        NoticeEntry.TABLE_NAME1,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            // "location/*"


            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }
    @Override
    public String getType(Uri uri) {

        // Use the Uri Matcher to determine what kind of URI this is.
        final int match1 = sUriMatcher1.match(uri);

        switch (match1) {
            case addition1: {
                return NoticeEntry.CONTENT_TYPE1;

            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

    }
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match1 = sUriMatcher1.match(uri);
        Uri returnUri;

        switch (match1) {
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
        final SQLiteDatabase db = new NoticeDBHelper(getContext()).getWritableDatabase();
        final int match1 = sUriMatcher1.match(uri);
        Log.d(LOG_TAG,"in bulk insert");
        switch (match1) {
            case addition1: {
                db.beginTransaction();
                int Count = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(NoticeEntry.TABLE_NAME1, null,value);
                        if (-1 != _id)
                        {
                            return Count++;
                        }

                    }
                    db.setTransactionSuccessful();
                }catch (Exception e){Log.e(LOG_TAG,e.getMessage(),e);}
                finally {
                    db.endTransaction();
                    db.close();
                }

                getContext().getContentResolver().notifyChange(uri, null);

            }
            default:
                return super.bulkInsert(uri, values);
        }
    }
}
*/