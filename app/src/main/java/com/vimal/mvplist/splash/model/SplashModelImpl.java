package com.vimal.mvplist.splash.model;

import android.content.Context;

import com.vimal.mvplist.Utils.SessionManger;
import com.vimal.mvplist.splash.SplashActivity;

public class SplashModelImpl implements SplashModel {
    private Context context;
    private SessionManger sessionManger;
    private long splashTimeOut = 5000;
    public SplashModelImpl(SplashActivity splashActivity) {
        context = splashActivity;
        sessionManger = new SessionManger(splashActivity);
    }

    @Override
    public long getSplashTimeOut() {
        return splashTimeOut;
    }
}
