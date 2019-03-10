package com.vimal.mvplist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vimal.mvplist.R;
import com.vimal.mvplist.home.model.data.HomeListResponse;

import java.util.ArrayList;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {
    public Context context;
    public ArrayList<HomeListResponse> listResponse;

    public HomeListAdapter(Context context, ArrayList<HomeListResponse> response){
        this.context = context;
        this.listResponse = response;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_adapter, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        HomeListResponse response = listResponse.get(position);
        viewHolder.headerTextView.setText(response.subject);
        viewHolder.subHeaderTextView.setText(response.message);
        Glide.with(context)
                .load(response.picture)
                .apply(RequestOptions.circleCropTransform())
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return listResponse.size();
    }

    public void setItems(ArrayList<HomeListResponse> response) {
        this.listResponse = response;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private AppCompatTextView headerTextView, subHeaderTextView;
        private AppCompatImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headerTextView = itemView.findViewById(R.id.headertext);
            subHeaderTextView = itemView.findViewById(R.id.subheadertext);
            imageView = itemView.findViewById(R.id.icon);
        }
    }
}
