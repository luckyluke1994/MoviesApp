package com.example.maidaidien.moviesapp.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.maidaidien.moviesapp.app.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by mai.dai.dien on 11/01/2017.
 */
public class DetailActivity extends AppCompatActivity {
    private Movie mMovie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setupToolbar();
        mMovie = (Movie) getIntent().getExtras().getSerializable(MoviesFragment.MOVIE_EXTRA_TEXT);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .add(R.id.container, DetailFragment.getInstance(mMovie))
                .commit();
        }

        ImageView posterImageView = (ImageView) findViewById(R.id.poster_imageview);
        String imageUrl = Utils.getImagePath(mMovie.getMovieBackdropPath());
        Picasso.with(this).load(imageUrl).into(posterImageView);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout =
            (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        collapsingToolbarLayout.setTitle("");
        appBarLayout.setExpanded(true);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }
}
