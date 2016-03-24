package com.retrofit.wangfei.flux_retrofit_rxjava.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wangfei on 2016/3/23.
 */
public class GitHubUser {
    @SerializedName("status")
    public String status;

    @SerializedName("msg")
    public String msg;
}
