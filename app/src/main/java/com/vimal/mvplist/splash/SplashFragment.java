package com.vimal.mvplist.splash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vimal.mvplist.BaseFragment;
import com.vimal.mvplist.R;
import com.vimal.mvplist.home.HomeActivity;

public class SplashFragment extends BaseFragment implements SplashConnector.View{
    private SplashConnector.Presenter presenter;

    public static SplashFragment newInstance(){
        return new SplashFragment();
    }

    @Override
    public void setPresenter(SplashConnector.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.frag_splash, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter!=null)
            presenter.start();
    }

    @Override
    public void onDestroy() {
        presenter.takeActionOnDestroy();
        super.onDestroy();
    }

    @Override
    public void moveToHomeScreen() {
        if (getActivity()!=null){
            HomeActivity.startActivity(getActivity());
            getActivity().finish();
        }
    }
}
