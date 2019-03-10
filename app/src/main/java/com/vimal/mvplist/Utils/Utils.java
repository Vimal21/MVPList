package com.vimal.mvplist.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.transition.Explode;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vimal.mvplist.BuildConfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.util.Log.INFO;

/**
 * Common Access methods and variable , constants
 * Created by vimal.
 */

public class Utils {


    /**
     * To check Lollipop verison sdk
     *
     * @return true equal and higher , false for lower 6.0
     */
    public static boolean isLollipopHigher() {

        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * get the Device UUID<br/>
     * <b>Res:</b>  https://developer.android.com/training/articles/user-data-ids.html
     *
     * @return device unique id
     */
    public static String getDeviceId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Convert String to MD5
     *
     * @param text src text
     * @return converted MD5
     */
    public static String getMD5(final String text) {
        try {
            final java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            final byte[] array = md.digest(text.getBytes());
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString(array[i] & 0xFF | 0x100).substring(1, 3));
            }
            // return sb.toString();
            return sb.toString();
        } catch (final Exception e) {
            //e.printStackTrace();
            return "";
        }
    }


    /**
     * To check Kitkat version sdk
     *
     * @return true equal and higher , false for lower 6.0
     */
    public static boolean isKitkatHigher() {

        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean isHigher(int versionApiLevel) {

        return Build.VERSION.SDK_INT >= versionApiLevel;
    }

    public static boolean isOreo() {

        return android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O;
    }

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        try {
            transaction.commit();
        } catch (Exception ignored) {

        }

    }

    /**
     * Get the Retrofit instance
     *
     * @return Retrofit
     */
    public static Retrofit getRetrofit(String baseDomain, Context context) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Platform.get().log(INFO, message, null);
                FileLogger fileLogger = FileLogger.getInstance();
                String TAG = "Okhttp";
                fileLogger.appendLog(TAG, message);
                fileLogger.flush(TAG);
            }
        });
        // set your desired log level
        logging.setLevel(BuildConfig.isShowLog ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new NoConnectivityInterceptor(context)).addInterceptor(logging).connectTimeout(0, TimeUnit.MINUTES).readTimeout(0, TimeUnit.MINUTES).build();
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
        return new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseDomain)
                .build();
    }

    /**
     * To check network connection available or not
     *
     * @return true if available else no, not available
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

    }
}
