package com.vimal.mvplist.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.vimal.mvplist.BaseFragment;
import com.vimal.mvplist.R;
import com.vimal.mvplist.adapter.HomeListAdapter;
import com.vimal.mvplist.home.model.data.HomeListResponse;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment implements HomeConnector.View {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private HomeConnector.Presenter presenter;
    private HomeListAdapter homeAdapter;
    private AppCompatTextView noDataFound;
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.frag_home, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        noDataFound = view.findViewById(R.id.no_data_txt);
        progressBar = view.findViewById(R.id.progressBar);
        return view;
    }

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Override
    public void setPresenter(HomeConnector.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(presenter!=null)
            presenter.start();
    }

    @Override
    public void updateListView(ArrayList<HomeListResponse> homeListResponse) {
        isVisibleRecyclerView(true);
        if(homeAdapter == null){
            homeAdapter = new HomeListAdapter(getContext(), homeListResponse);
            recyclerView.setAdapter(homeAdapter);
        } else {
            homeAdapter.setItems(homeListResponse);
            homeAdapter.notifyDataSetChanged();
        }
    }

    private void isVisibleRecyclerView(boolean show) {
        progressBar.setVisibility(show ? View.GONE : View.VISIBLE);
        noDataFound.setVisibility(show ? View.GONE : View.VISIBLE);
        recyclerView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void noDataListView() {
        isVisibleRecyclerView(false);
        noDataFound.setText(R.string.no_data);
    }

    @Override
    public void updateNetworkErrorView(String errorMessage) {
        isVisibleRecyclerView(false);
        noDataFound.setText(errorMessage);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.clearAPICall();
    }
}
