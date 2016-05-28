package com.beraaksoy.loaderapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.Arrays;
import java.util.List;

/**
 * Created by beraaksoy on 5/28/16.
 * AsyncTaskLoader is great for using with Databases to load query results in a background thread.
 * <p/>
 * Loader goes through few steps.
 * 1) START state, ie. isStarted
 * 2) STOP state
 * 3) RESET state, which means its being destroyed
 * <p/>
 * Firstly, override onStartLoading. When you call initLoader from the client, onStartLoading will be
 * called for the first time.
 * <p/>
 * loadInBackground() performs the load in a background thread and returns the result.
 * <p/>
 * Then, deliverResult() takes over and delivers the result of loadInBackground back to whoever is expecting this classes
 * callback.
 */
public class StringLoader extends AsyncTaskLoader<List<String>> {

    private List<String> cachedData;

    public StringLoader(Context context) {
        super(context);
    }


    @Override
    protected void onStartLoading() {
        if (cachedData == null) {
            forceLoad(); // forces loadInBackground to be called
        } else {
            super.deliverResult(cachedData); //if we have cached data, deliver that instead of loading again
        }
    }

    @Override
    public List<String> loadInBackground() {
        //Trying to block our loader from doing anything to simulate a database query
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String> data = Arrays.asList(getContext().getResources().getStringArray(R.array.flowers));
        return data;
    }

    @Override
    public void deliverResult(List<String> data) {
        // cachedData will get a copy of the data and also super.deliverResult(data)
        // will deliver the data result to whoever is expecting the resultset
        cachedData = data;
        super.deliverResult(data);
    }
}
