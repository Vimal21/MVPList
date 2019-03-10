package com.vimal.mvplist.home;

import com.vimal.mvplist.BasePresenter;
import com.vimal.mvplist.BaseView;
import com.vimal.mvplist.home.model.data.HomeListResponse;

import java.util.ArrayList;

public interface HomeConnector {

    interface Presenter extends BasePresenter{
        void clearAPICall();
    }

    interface View extends BaseView<Presenter>{

        void updateListView(ArrayList<HomeListResponse> homeListResponse);

        void noDataListView();

        void updateNetworkErrorView(String errorMessage);
    }
}
