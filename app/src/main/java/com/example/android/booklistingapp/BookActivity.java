package com.example.android.booklistingapp;
/**
 * This project is done by Khaidem Sandip Singha under the Udacity Android Foundations Nanodegree program.
 *
 * I confirm that this submission is my own work. I have not used code from any other Udacity student's or graduate's submission of the same project.
 * I understand that Udacity will check my submission for plagiarism, and that failure to adhere to the Udacity Honor Code may result in the cancellation of my
 * enrollment.
 */

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
public class BookActivity extends AppCompatActivity implements LoaderCallbacks<List<BookList>>{

    private static final String LOG_TAG = BookActivity.class.getName();

    /** URL for bookList*/
    public static String BOOKS_REQUEST_URL = " https://www.googleapis.com/books/v1/volumes?q=android&maxResults=20\n";

    /**
     * Constant value for the bookList loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int BOOKLIST_LOADER_ID = 1;

    private int book_id = 1;

    /** Adapter for the list of books */
    private BookAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        // Find a reference to the {@link ListView} in the layout
        ListView bookListView = findViewById(R.id.list);

        //Find a reference to the (@link button) in the layout
        Button button = findViewById(R.id.button);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        //bookListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of books as input
        mAdapter = new BookAdapter(this, new ArrayList<BookList>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(mAdapter);

        LoaderManager loaderManager = null;
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        //If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(BOOKLIST_LOADER_ID, null, BookActivity.this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with the buying option about the selected books.
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Find the current book that was clicked`on
                BookList currentBook = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri bookListUri = Uri.parse(currentBook.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, bookListUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        //Search button for searching the book from the url link
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                book_id = book_id + 1;
                EditText editText = findViewById(R.id.edit_text);
                String searchBook = editText.getText().toString();
                Log.i(LOG_TAG,searchBook);

                //Searching book in the url link
                BOOKS_REQUEST_URL = "  https://www.googleapis.com/books/v1/volumes?q="+searchBook+"&maxResults=20" + "\n";

                LoaderManager loaderManager = null;
                // Get a reference to the ConnectivityManager to check state of network connectivity
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                // Get details on the currently active default data network
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                //If there is a network connection, fetch data
                if (networkInfo != null && networkInfo.isConnected()) {
                    // Get a reference to the LoaderManager, in order to interact with loaders.
                    loaderManager = getLoaderManager();

                    // Initialize the loader. Pass in the int ID constant defined above and pass in null for
                    // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
                    // because this activity implements the LoaderCallbacks interface).
                    loaderManager.restartLoader(BOOKLIST_LOADER_ID, null, BookActivity.this);
                } else {
                    // Otherwise, display error
                    // First, hide loading indicator so error message will be visible
                    View loadingIndicator = findViewById(R.id.loading_indicator);
                    loadingIndicator.setVisibility(View.GONE);

                    // Update empty state with no connection error message
                    mEmptyStateTextView.setText(R.string.no_internet_connection);
                }
            }
        });
    }
    @Override
    public Loader<List<BookList>> onCreateLoader ( int i, Bundle bundle) {
        Log.i("BookListActivtiy","Loader Created "+BOOKS_REQUEST_URL);
        // Create a new loader for the given URL
        return new BookLoader(this, BOOKS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader < List < BookList >> loader, List < BookList > bookLists){
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Clear the adapter of previous books data
        mAdapter.clear();

        // If there is a valid list of {@link Booklist}, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (bookLists != null && !bookLists.isEmpty()) {
            mAdapter.addAll(bookLists);

        }else{
            // Set empty state text to display "No Books found."
            mEmptyStateTextView.setText(R.string.no_books);
        }
    }

    @Override
    public void onLoaderReset (Loader < List < BookList >> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}

