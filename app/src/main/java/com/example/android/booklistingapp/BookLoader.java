package com.example.android.booklistingapp;
/**
 * This project is done by Khaidem Sandip Singha under the Udacity Android Foundations Nanodegree program.
 *
 * I confirm that this submission is my own work. I have not used code from any other Udacity student's or graduate's submission of the same project.
 * I understand that Udacity will check my submission for plagiarism, and that failure to adhere to the Udacity Honor Code may result in the cancellation of my
 * enrollment.
 */
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Owner on 26-03-2018.
 */

public class BookLoader extends AsyncTaskLoader<List<BookList>> {
    /** Tag for log messages */
    private static final String LOG_TAG = BookLoader.class.getName();

    /** Query Url*/
    private String mUrl;

    /**
     * Constructs a new (@link BookLoader).
     * @param context of the activity
     * @param url to load the data
     */
    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.i(LOG_TAG,"Loader starts Loading");
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<BookList> loadInBackground(){
        if (mUrl == null){
            return null;
        }

        // Perform the network request, parse the response, and extract a list of books
        List<BookList> bookLists = QueryUtils.fetchBookListData(mUrl);
        Log.i(LOG_TAG,"Loader Loading in Background");
        return bookLists;
    }
}
