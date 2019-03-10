package com.vimal.mvplist.Utils;

import android.content.Context;

import com.vimal.mvplist.R;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by developer on 6/11/17.
 */

public class NoConnectivityInterceptor implements Interceptor {
    private Context context;

    NoConnectivityInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!Utils.isNetworkAvailable(context)) {
            throw new NoConnectivityException();
        }
        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }

    public class NoConnectivityException extends IOException {
        @Override
        public String getMessage() {
            return context.getString(R.string.err_network);
        }
    }
}
