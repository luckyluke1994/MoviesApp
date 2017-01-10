package com.example.maidaidien.moviesapp.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.maidaidien.moviesapp.app.model.Movie;

import java.util.List;

/**
 * Created by mai.dai.dien on 10/01/2017.
 */
public class MovieAdapter extends BaseAdapter {
    private Context mContext;
    private List<Movie> mMovies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.mContext = context;
        this.mMovies = movies;
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
        return null;
    }
}
