package com.retrofit.wangfei.flux_retrofit_rxjava.request;

import com.retrofit.wangfei.flux_retrofit_rxjava.model.GitHubUser;
import com.retrofit.wangfei.flux_retrofit_rxjava.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-03-23
 * Time: 10:32
 * Description:
 */
public interface GitHubApi {

    @POST("index.php/user/login")
    Observable<GitHubUser> login(@Body User user);
}
