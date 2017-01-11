package com.example.maidaidien.moviesapp.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
}
