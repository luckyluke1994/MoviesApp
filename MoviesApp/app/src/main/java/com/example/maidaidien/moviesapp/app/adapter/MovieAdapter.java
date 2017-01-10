package com.example.maidaidien.moviesapp.app.adapter;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maidaidien.moviesapp.app.R;
import com.example.maidaidien.moviesapp.app.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mai.dai.dien on 10/01/2017.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(Activity context, List<Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Movie movie = getItem(i);
        if (movie == null) return view;

        final String MOVIE_POSTER_PATH_BASE_URL =
            "http://image.tmdb.org/t/p/";
        final String MOVIE_POSTER_SIZE = "w185";
        Uri uri = Uri.parse(MOVIE_POSTER_PATH_BASE_URL).buildUpon()
            .appendPath(MOVIE_POSTER_SIZE)
            .appendEncodedPath(movie.getMoviePosterPath())
            .build();

        View rootView = view;
        if (rootView == null) {
            rootView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_movie, viewGroup,
                false);
        }

        ImageView posterImageView = (ImageView) rootView.findViewById(R.id.poster_imageview);
        TextView originalTitle = (TextView) rootView.findViewById(R.id.original_title_textview);
        TextView ratedTextView = (TextView) rootView.findViewById(R.id.rated_textview);
        Picasso.with(getContext()).load(uri.toString()).into(posterImageView);
        originalTitle.setText(movie.getOriginalTitle());
        ratedTextView.setText(Double.toString(movie.getRated()));
        return rootView;
    }
}
