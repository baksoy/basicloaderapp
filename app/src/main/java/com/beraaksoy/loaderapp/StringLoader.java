package com.beraaksoy.loaderapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.Arrays;
import java.util.List;

/**
 * Created by beraaksoy on 5/28/16.
 * AsyncTaskLoader is great for using with Databases to load query results in a background thread.
 *
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


    public StringLoader(Context context) {
        super(context);
    }


    @Override
    protected void onStartLoading() {
        forceLoad(); // forces loadInBackground to be called
    }

    @Override
    public List<String> loadInBackground() {
        List<String> data = Arrays.asList(getContext().getResources().getStringArray(R.array.flowers));
        return data;
    }

    @Override
    public void deliverResult(List<String> data) {
        //super.deliverResult(data) will deliver the data result to wherever the results are expected
        super.deliverResult(data);
    }
}
