package com.example.maidaidien.moviesapp.app.model;

import java.io.Serializable;

/**
 * Created by mai.dai.dien on 10/01/2017.
 */
public class Movie implements Serializable {
    private int mMovieId;
    private String mMoviePosterPath;
    private String mOriginalTitle;
    private String mOverview;
    private String mReleaseDate;
    private Double mRated;
    private long mVoteCount;

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

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public Double getRated() {
        return mRated;
    }

    public void setRated(Double rated) {
        mRated = rated;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public long getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(long voteCount) {
        mVoteCount = voteCount;
    }
}
