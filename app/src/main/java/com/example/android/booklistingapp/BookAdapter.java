package com.example.android.booklistingapp;
/**
 * This project is done by Khaidem Sandip Singha under the Udacity Android Foundations Nanodegree program.
 *
 * I confirm that this submission is my own work. I have not used code from any other Udacity student's or graduate's submission of the same project.
 * I understand that Udacity will check my submission for plagiarism, and that failure to adhere to the Udacity Honor Code may result in the cancellation of my
 * enrollment.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Owner on 26-03-2018.
 */

public class BookAdapter extends ArrayAdapter<BookList> {
    /**
     * Constructs a new {@link BookAdapter}.
     *
     * @param context of the app
     * @param bookList is the list of books, which is the data source of the adapter
     */
    public BookAdapter(Context context, List<BookList> bookList){
        super(context, 0, bookList);
    }

    /**
     * Returns a list item view that displays information about the books at the given position
     * in the list of books.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //Find the book at the given position in the list of books
        BookList currentBook = getItem(position);

        //Find the TextView with view ID book
        TextView bookName = listItemView.findViewById(R.id.book);
        bookName.setText(currentBook.getName());

        //Find the TextView with view ID author
        TextView authorName = listItemView.findViewById(R.id.author);
        authorName.setText(currentBook.getAuthor());

        //Find the TextView with view ID publisher
        TextView publisherName = listItemView.findViewById(R.id.published);
        publisherName.setText(currentBook.getPublished());

        return  listItemView;
    }
}
