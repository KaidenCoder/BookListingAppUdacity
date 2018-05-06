package com.example.android.booklistingapp;
/**
 * This project is done by Khaidem Sandip Singha under the Udacity Android Foundations Nanodegree program.
 *
 * I confirm that this submission is my own work. I have not used code from any other Udacity student's or graduate's submission of the same project.
 * I understand that Udacity will check my submission for plagiarism, and that failure to adhere to the Udacity Honor Code may result in the cancellation of my
 * enrollment.
 */
/**
 * Created by Owner on 26-03-2018.
 */

public class BookList {

    /** Name of the book */
    private String mName;

    /** Name of the author */
    private String mAuthor;

    /** Name of the publisher */
    private String mPublished;

    /** Website of the book */
    private String mUrl;

    /**
     * Constructs a new (@link BookList) object
     * @param name is the name of the book
     * @param author is the author of the book
     * @param published is the published date of the book
    //* @param  is the url of the book
     */
    public BookList(String name, String author, String published, String url){
        mName = name;
        mAuthor = author;
        mPublished = published;
        mUrl = url;
    }

    /**
     * Returns the name of the book
     */
    public String getName(){
        return mName;
    }

    /**
     * Returns the author of the book
     */
    public String getAuthor(){
        return mAuthor;
    }

    /**
     * Returns the publisher of the book
     */
    public String getPublished(){
        return mPublished;
    }

    /**
     * Returns the website url of the book
     */
    public String getUrl(){
        return mUrl;
    }
}
