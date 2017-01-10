package com.example.maidaidien.moviesapp.app.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.maidaidien.moviesapp.app.R;
import com.example.maidaidien.moviesapp.app.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mai.dai.dien on 10/01/2017.
 */
public class MovieAdapter extends BaseAdapter {
    private Context mContext;
    private List<Movie> mMovies;
    private LayoutInflater mLayoutInflater;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.mContext = context;
        this.mMovies = movies;
        mLayoutInflater =
            (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return (mMovies == null) ? 0 : mMovies.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Movie movie = mMovies.get(i);
        if (movie == null) return view;

        final String MOVIE_POSTER_PATH_BASE_URL =
            "http://image.tmdb.org/t/p/";
        final String MOVIE_POSTER_SIZE = "w185";
        Uri uri = Uri.parse(MOVIE_POSTER_PATH_BASE_URL).buildUpon()
            .appendPath(MOVIE_POSTER_SIZE)
            .appendEncodedPath(movie.getMoviePosterPath())
            .build();

        View rootView = view;
        if (view == null) {
            rootView = mLayoutInflater.inflate(R.layout.list_item_movie, viewGroup, false);
        }

        ImageView posterImageView = (ImageView) rootView.findViewById(R.id.poster_imageview);
        Picasso.with(mContext).load(uri.toString()).into(posterImageView);
        return rootView;
    }
}
