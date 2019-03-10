package com.vimal.mvplist.Utils;

import android.util.Log;

import com.vimal.mvplist.BuildConfig;


/**
 * Print the log information
 * Created by vimal.
 */

public class Logger {

    private static String TAG = "Oway-Passenger";

    public static void i(String tag, String message) {
        if (BuildConfig.isShowLog) {
            Log.i(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (BuildConfig.isShowLog) {
            Log.e(tag, message);
        }

    }

    public static void d(String tag, String message) {
        if (BuildConfig.isShowLog) {
            Log.d(tag, message);
        }

    }

    public static void i(String message) {
        i(TAG, message);
    }

    public static void e(String message) {
        e(TAG, message);
    }

    public static void d(String message) {
        d(TAG, message);
    }
}
