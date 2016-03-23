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

    private String from;
    private String username;
    private String userpass;

    public User(String from, String username, String userpass) {
        this.from = from;
        this.username = username;
        this.userpass = userpass;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getFrom() {
        return from;
    }

    public String getUsername() {
        return username;
    }

    public String getUserpass() {
        return userpass;
    }

    @Override
    public String toString() {
        return "User{" +
                "from='" + from + '\'' +
                ", username='" + username + '\'' +
                ", userpass='" + userpass + '\'' +
                '}';
    }
}
