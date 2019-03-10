package com.vimal.mvplist.splash;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.vimal.mvplist.R;
import com.vimal.mvplist.Utils.FileLogger;
import com.vimal.mvplist.Utils.Utils;
import com.vimal.mvplist.splash.model.SplashModelImpl;

public class SplashActivity extends AppCompatActivity {

    private final static String TAG = "Splash";
    private SplashFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_container);
        FileLogger.getInstance().startLoggerForTag(TAG);
        if(fragment == null)
            fragment = SplashFragment.newInstance();
        new SplashPresenter(new SplashModelImpl(this), fragment, TAG);
        Utils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.lay_fr_container);
        findViewById(R.id.ib_back).setVisibility(View.GONE);
    }
}
