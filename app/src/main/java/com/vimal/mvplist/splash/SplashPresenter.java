package com.vimal.mvplist.splash;

import android.os.Handler;

import com.vimal.mvplist.Utils.CallBack;
import com.vimal.mvplist.Utils.FileLogger;
import com.vimal.mvplist.splash.model.SplashModel;


public class SplashPresenter implements SplashConnector.Presenter {
    private String tag;
    private  SplashModel model;
    private SplashConnector.View view;
    private Handler splashDelayHandler = new Handler();
    private FileLogger fileLogger;
    private Runnable splashScreenRunnable = new Runnable() {
        @Override
        public void run() {
            view.moveToHomeScreen();
        }
    };
    SplashPresenter(SplashModel splashModel, SplashConnector.View view, String tag){
        this.model = splashModel;
        this.view = view;
        this.tag = tag;
        this.view.setPresenter(this);
        fileLogger = FileLogger.getInstance();
    }

    @Override
    public void start() {
        holdSplashScreen(model.getSplashTimeOut());
    }

    private void holdSplashScreen(long splashTimeOut) {
        splashDelayHandler.postDelayed(splashScreenRunnable, splashTimeOut);
    }

    @Override
    public void Filelogger(String log) {
        fileLogger.appendLog(tag, log);
    }

    @Override
    public void FlushLogger() {
        fileLogger.flush(tag);
    }

    @Override
    public void takeActionOnDestroy() {
        splashDelayHandler.removeCallbacks(splashScreenRunnable);
        splashDelayHandler = null;
    }
}
