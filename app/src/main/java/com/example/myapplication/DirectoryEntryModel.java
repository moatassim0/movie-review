package com.example.myapplication;

import android.graphics.drawable.Drawable;

public class DirectoryEntryModel {
    Drawable poster;
    int movieID;


    public DirectoryEntryModel(int movieID, Drawable poster) {
        this.movieID = movieID;
        this.poster = poster;
    }

    public Drawable getPoster() {
        return poster;
    }

    public int getMovieID() {
        return movieID;
    }
}
