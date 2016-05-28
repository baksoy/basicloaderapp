package com.beraaksoy.loaderapp;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private LoaderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new LoaderAdapter(this);

        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);

        getSupportLoaderManager().initLoader(R.id.string_loader_id, null, loaderCallbacks);

    }

    private LoaderManager.LoaderCallbacks<List<String>> loaderCallbacks = new LoaderManager.LoaderCallbacks<List<String>>() {
        @Override
        public Loader<List<String>> onCreateLoader(int id, Bundle args) {
            return new StringLoader(getApplicationContext());
        }

        @Override
        public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
            // We are saying, every time our load is finished call adapter.swapdata()
            // We are getting back the data sent by StringLoader class deliverResult() method
            adapter.swapData(data);

        }

        @Override
        public void onLoaderReset(Loader<List<String>> listLoader) {
            // loader is destroyed at this point and there is no data to display. So,
            // we'll empty out the list so there'll be nothing to display.
            adapter.swapData(Collections.<String>emptyList());
        }
    };
}
