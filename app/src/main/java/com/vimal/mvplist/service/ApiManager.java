package com.vimal.mvplist.service;

import com.vimal.mvplist.home.model.data.HomeListResponse;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiManager {
    String segments = "/json/";

    @GET(segments + "inbox.json")
    Call<ArrayList<HomeListResponse>> getHomeListAPI();
}
