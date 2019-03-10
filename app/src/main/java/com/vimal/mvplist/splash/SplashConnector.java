package com.vimal.mvplist.splash;

import com.vimal.mvplist.BasePresenter;
import com.vimal.mvplist.BaseView;

public interface SplashConnector {

    interface Presenter extends BasePresenter {
        void takeActionOnDestroy();
    }

    interface View extends BaseView<Presenter>{
        void moveToHomeScreen();
    }

}
