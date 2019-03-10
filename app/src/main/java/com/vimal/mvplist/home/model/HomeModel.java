package com.vimal.mvplist.home.model;

import com.vimal.mvplist.Utils.CallBack;
import com.vimal.mvplist.home.model.data.HomeListResponse;

import java.util.ArrayList;

public interface HomeModel {

    void getHomeListData(CallBack<ArrayList<HomeListResponse>> callBack);

    void clearAPICall();
}
