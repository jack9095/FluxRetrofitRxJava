package com.retrofit.wangfei.flux_retrofit_rxjava.request;

import com.retrofit.wangfei.flux_retrofit_rxjava.model.GitHubUser;
import com.retrofit.wangfei.flux_retrofit_rxjava.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @FormUrlEncoded
    @POST("wangfeigit/FluxRetrofitRxJava.git") // 请求接口路径路径
    Observable<GitHubUser> login(@Field("txtUserName") String username,@Field("txtPassword") String password);
}
