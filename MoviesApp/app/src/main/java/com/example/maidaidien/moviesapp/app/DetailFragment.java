package com.example.maidaidien.moviesapp.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maidaidien.moviesapp.app.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by mai.dai.dien on 11/01/2017.
 */
public class DetailFragment extends Fragment {
    private Movie mMovie;

    public static DetailFragment getInstance(Movie movie) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(MoviesFragment.MOVIE_EXTRA_TEXT, movie);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovie = (Movie) getArguments().getSerializable(MoviesFragment.MOVIE_EXTRA_TEXT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // inflate layout
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        // findview
        ImageView imageView = (ImageView) rootView.findViewById(R.id.thumb_image);
        TextView titleTextView = (TextView) rootView.findViewById(R.id.title);
        TextView overviewTextView = (TextView) rootView.findViewById(R.id.overview_textview);
        TextView releaseDateTextView = (TextView) rootView.findViewById(R.id.release_date_value);
        TextView ratedAverageTextView = (TextView) rootView.findViewById(R.id.star_rate_value);
        TextView rateCountTextView = (TextView) rootView.findViewById(R.id.rate_count_value);
        // bind data to view
        titleTextView.setText(mMovie.getOriginalTitle());
        overviewTextView.setText(mMovie.getOverview());
        ratedAverageTextView
            .setText(String.format(getString(R.string.string_vote_rate), mMovie.getRated(), 10.0));
        rateCountTextView
            .setText(String.format(getString(R.string.string_vote_count), mMovie.getVoteCount()));
        releaseDateTextView.setText(Utils.getReadableDateString(mMovie.getReleaseDate()));
        String imageUrl = Utils.getImagePath(mMovie.getMoviePosterPath());
        Picasso.with(getActivity()).load(imageUrl).into(imageView);
        // return view
        return rootView;
    }
}
