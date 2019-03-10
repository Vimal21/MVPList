package com.vimal.mvplist.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vimal.mvplist.R;
import com.vimal.mvplist.Utils.FileLogger;
import com.vimal.mvplist.Utils.Utils;
import com.vimal.mvplist.home.model.HomeModelImpl;

public class HomeActivity extends AppCompatActivity {
    private final static String TAG = "Home";
    private HomeFragment fragment;

    public static void startActivity(Activity activity){
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_container);
        FileLogger.getInstance().startLoggerForTag(TAG);
        if(fragment == null)
            fragment = HomeFragment.newInstance();
        new HomePresenter(new HomeModelImpl(this), fragment, TAG);
        Utils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.lay_fr_container);
        findViewById(R.id.ib_back).setVisibility(View.GONE);
    }
}
