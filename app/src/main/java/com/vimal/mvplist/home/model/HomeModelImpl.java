package com.vimal.mvplist.home.model;

import android.content.Context;

import com.vimal.mvplist.AppController;
import com.vimal.mvplist.Utils.CallBack;
import com.vimal.mvplist.Utils.SessionManger;
import com.vimal.mvplist.home.HomeActivity;
import com.vimal.mvplist.home.model.data.HomeListResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeModelImpl implements HomeModel {
    private Context context;
    private SessionManger sessionManger;
    private Call<ArrayList<HomeListResponse>> homeResponse;
    public HomeModelImpl(HomeActivity homeActivity){
        context = homeActivity;
        sessionManger = new SessionManger(homeActivity);
    }

    @Override
    public void getHomeListData(final CallBack<ArrayList<HomeListResponse>> callBack) {
        if(homeResponse!=null)
            homeResponse.cancel();

        final AppController appController = AppController.getInstance();
        homeResponse = appController.getApiManager().getHomeListAPI();
        homeResponse.enqueue(new Callback<ArrayList<HomeListResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<HomeListResponse>> call, Response<ArrayList<HomeListResponse>> response) {
                ArrayList<HomeListResponse> homeListResponse = response.body();
                if(response.isSuccessful() && homeListResponse!=null){
                    callBack.success(homeListResponse);
                } else {
                    callBack.responseFailure(homeListResponse);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<HomeListResponse>> call, Throwable t) {
                    callBack.connectionFailure(t);
            }
        });
    }

    @Override
    public void clearAPICall() {
        if(homeResponse!=null)
            homeResponse.cancel();
    }
}
