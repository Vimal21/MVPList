package com.vimal.mvplist;

import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.vimal.mvplist.Utils.FileLogger;
import com.vimal.mvplist.Utils.Utils;
import com.vimal.mvplist.service.ApiManager;

public class AppController extends MultiDexApplication {

    private static AppController INSTANCE;
    private static final String baseDomain = "https://api.androidhive.info";
    private ApiManager apiManager;

    /**
     * Return the instance of the application controller
     * @return
     */
    public static AppController getInstance(){
        if(INSTANCE  == null){
            INSTANCE = new AppController();
        }
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        FileLogger.getInstance().attachApplication(this);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /**
     * Get the API Manager for calling API
     *
     * @return com.owayride.retrofit.apiManager instance
     */
    public ApiManager getApiManager() {
        if (apiManager == null) {
            apiManager = Utils.getRetrofit(baseDomain, this).create(ApiManager.class);
        }
        return apiManager;
    }

}
