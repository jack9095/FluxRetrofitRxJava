package com.retrofit.wangfei.flux_retrofit_rxjava.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-03-23
 * Time: 10:32
 * Description:  网络请求接口的封装
 */
public class GitHubApiUtils {

    private static GitHubApiUtils mInstance;
    private GitHubApi gitHubApi;

    private GitHubApiUtils() {
        /* JSON 解析 */
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.kuaigongchang.cn/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient())
                .build();

        gitHubApi = retrofit.create(GitHubApi.class);
    }

    /* 单例 */
    public static GitHubApiUtils getInstance() {
        if (mInstance == null) {
            synchronized (GitHubApiUtils.class) {
                if (mInstance == null) {
                    mInstance = new GitHubApiUtils();
                }
            }
        }
        return mInstance;
    }

    public GitHubApi getGitHubApi() {
        return gitHubApi;
    }

    /** 获取 OkHttpClient */
    private OkHttpClient okHttpClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS).setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(logging).addInterceptor(headerInterceptor);

        return builder.build();
    }

    /** 请求头 */
    Interceptor headerInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .addHeader("User-Agent", "Test")
                    .addHeader("Accept", "application/vnd.github.v3+json")
//                .addHeader("Authorization", "")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }
    };
}
