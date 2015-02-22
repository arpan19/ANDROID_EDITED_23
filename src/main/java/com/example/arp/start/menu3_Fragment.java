package com.example.arp.start;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.arp.start.data.NoticeContract;
import com.example.arp.start.data.NoticeContract.NoticeEntry;

/**
 * Created by Arp on 2/8/2015.
 */
public class menu3_Fragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>
{
    public static final String LOG_TAG = "MyApp3";
    View rootview;

    private  String mLocation;
    private  static  final int FORECAST_LOADER=0;

    private  static final String[] FORECAST_COLUMNS ={
            NoticeContract.NoticeEntry.TABLE_NAME1+"."+ NoticeEntry._ID,
            NoticeContract.NoticeEntry.COLUMN_SERIAL_NUMBER,
            NoticeContract.NoticeEntry.COLUMN_DATE,
            NoticeContract.NoticeEntry.COLUMN_NOTICE,

    };


    public static final int COL_SERIAL_NUMBER = 0;
    public static final int COL_DATE = 1;
    public static final int COL_NOTICE = 2;
    public menu3_Fragment() {

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(FORECAST_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);

    }


    String[] s = new String[20];
    private SimpleCursorAdapter checkingAdapter;
    private SimpleCursorAdapter checkingAdapter1;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }//for a refresh menu ,has a menu


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.refresh, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)

    {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {

            FetchWeatherTask2 weatherTask = new FetchWeatherTask2(getActivity());
            weatherTask.execute("94043");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        checkingAdapter = new SimpleCursorAdapter(


                getActivity(),
                R.layout.list_notice,
                null,
                FORECAST_COLUMNS,
                new int[]{
                   R.id.serial_number1,
                        R.id.date1,
                        R.id.notice,
                },
                0

        );
        Log.d(LOG_TAG,"formed ca1");

        rootview = inflater.inflate(R.layout.menu3_layout, container, false);
        ListView listView = (ListView) rootview.findViewById(R.id.all_companies3);
        listView.setAdapter(checkingAdapter);
        Log.d(LOG_TAG,"reached ca1");
        return rootview;
    }



    private void updateWeather() {
        FetchWeatherTask2 weatherTask = new FetchWeatherTask2(getActivity());

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String location = prefs.getString("", "");
        new FetchWeatherTask2(getActivity()).execute(location);



    }

    @Override

    public void onStart() {

        super.onStart();
        updateWeather();
    }




    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args)
    {





        Uri tableUri= NoticeContract.NoticeEntry.CONTENT_URI1;
        Log.d(LOG_TAG,"formed uri ca1"+tableUri);
                       /*     String sortOrder = WeatherEntry.COLUMN_DATETEXT + " ASC";

                    mLocation = Utility.getPreferredLocation(getActivity());
           Uri weatherForLocationUri = WeatherEntry.buildWeatherLocationWithStartDate(
                            mLocation, startDate); */

        return new CursorLoader
                (
                        getActivity(),
                        tableUri,
                        FORECAST_COLUMNS,
                        null,
                        null,
                        null
                );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        checkingAdapter.swapCursor(data);
        Log.d(LOG_TAG,"formed1 uri ca1");

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        checkingAdapter.swapCursor(null);
    }
}
