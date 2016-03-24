package com.retrofit.wangfei.flux_retrofit_rxjava.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-03-23
 * Time: 9:57
 * Description:  上传的参数
 */
public class User {

    private String txtUserName;
    private String txtPassword;

    public User() {
    }

    public User(String txtUserName, String txtPassword) {
        this.txtUserName = txtUserName;
        this.txtPassword = txtPassword;
    }
}
