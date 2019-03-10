package com.vimal.mvplist.home.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeListResponse {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("isImportant")
    @Expose
    public Boolean isImportant;
    @SerializedName("picture")
    @Expose
    public String picture;
    @SerializedName("from")
    @Expose
    public String from;
    @SerializedName("subject")
    @Expose
    public String subject;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("timestamp")
    @Expose
    public String timestamp;
    @SerializedName("isRead")
    @Expose
    public Boolean isRead;
}
