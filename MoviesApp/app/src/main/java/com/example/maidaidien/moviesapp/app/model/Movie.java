package com.example.maidaidien.moviesapp.app.model;

import java.io.Serializable;

/**
 * Created by mai.dai.dien on 10/01/2017.
 */
public class Movie implements Serializable {
    private int mMovieId;
    private String mMoviePosterPath;

    public Movie() {}

    public int getMovieId() {
        return mMovieId;
    }

    public void setMovieId(int movieId) {
        mMovieId = movieId;
    }

    public String getMoviePosterPath() {
        return mMoviePosterPath;
    }

    public void setMoviePosterPath(String moviePosterPath) {
        mMoviePosterPath = moviePosterPath;
    }
}
