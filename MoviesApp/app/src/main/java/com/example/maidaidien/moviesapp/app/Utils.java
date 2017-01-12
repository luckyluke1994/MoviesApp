package com.example.maidaidien.moviesapp.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by FRAMGIA\mai.dai.dien on 11/01/2017.
 */
public class Utils {
    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
            (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static String getImagePath(String path) {
        final String MOVIE_POSTER_PATH_BASE_URL =
            "http://image.tmdb.org/t/p/";
        final String MOVIE_POSTER_SIZE = "w185";
        Uri uri = Uri.parse(MOVIE_POSTER_PATH_BASE_URL).buildUpon()
            .appendPath(MOVIE_POSTER_SIZE)
            .appendEncodedPath(path)
            .build();
        return uri.toString();
    }

    public static String getReadableDateString(String time) {
        // Because the API returns a unix timestamp (measured in seconds),
        // it must be converted to milliseconds in order to be converted to valid date.
        SimpleDateFormat originFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd yyyy");
        Date date = null;
        try {
            date = originFormat.parse(time);
        } catch (ParseException e) {
            return time;
        }
        return shortenedDateFormat.format(date.getTime());
    }
}
