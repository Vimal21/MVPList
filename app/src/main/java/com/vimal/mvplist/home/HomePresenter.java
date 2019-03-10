package com.vimal.mvplist.home;

import com.vimal.mvplist.Utils.CallBack;
import com.vimal.mvplist.Utils.FileLogger;
import com.vimal.mvplist.home.model.HomeModel;
import com.vimal.mvplist.home.model.data.HomeListResponse;

import java.util.ArrayList;

public class HomePresenter implements HomeConnector.Presenter {
    private HomeModel model;
    private HomeConnector.View view;
    private String tag;
    private FileLogger fileLogger;

    HomePresenter(HomeModel model, HomeConnector.View view, String tag){
        this.model = model;
        this.view = view;
        this.tag = tag;
        view.setPresenter(this);
        fileLogger = FileLogger.getInstance();
    }

    @Override
    public void start() {
        model.getHomeListData(new CallBack<ArrayList<HomeListResponse>>() {
            @Override
            public void success(ArrayList<HomeListResponse> objects) {
                view.updateListView(objects);
            }

            @Override
            public void responseFailure(ArrayList<HomeListResponse> objects) {
                view.noDataListView();
            }

            @Override
            public void connectionFailure(Throwable errorThrowable) {
                view.updateNetworkErrorView(errorThrowable.getLocalizedMessage());
            }
        });
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
    public void clearAPICall() {
      model.clearAPICall();
    }
}
