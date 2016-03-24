package com.retrofit.wangfei.flux_retrofit_rxjava.action;

import android.support.annotation.NonNull;

import com.retrofit.wangfei.flux_retrofit_rxjava.dispatcher.Dispatcher;
import com.retrofit.wangfei.flux_retrofit_rxjava.model.GitHubUser;
import com.retrofit.wangfei.flux_retrofit_rxjava.model.User;
import com.retrofit.wangfei.flux_retrofit_rxjava.request.GitHubApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-03-23
 * Time: 12:45
 */
public class LoginActionCreator extends ActionCreator {

    private static LoginActionCreator loginActionCreator;

    // 单例
    public static LoginActionCreator getInstance(Dispatcher dispatcher) {
        if (loginActionCreator == null) {
            synchronized (LoginActionCreator.class) {
                if (loginActionCreator == null) {
                    loginActionCreator = new LoginActionCreator(dispatcher);
                }
            }
        }
        return loginActionCreator;
    }

    protected LoginActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }


    /**
     * 提取数据
     * @param user
     * @param pass
     */
    public void fetechData(String user,String pass) {
        dispatcher.dispatcher(new LoginAction(LoginAction.LOGIN_START, null));

        // Rx
        Observable.merge(getObservables(user,pass))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GitHubUser>() {
                    @Override
                    public void call(GitHubUser users) {
                        dispatcher.dispatcher(new LoginAction(LoginAction.LOGIN_VIEW, users));  // 把数据提交到action中
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        dispatcher.dispatcher(new LoginAction(LoginAction.LOGIN_ERROR, throwable));
                    }
                });
    }

    @NonNull
    private List<Observable<GitHubUser>> getObservables(String user,String pass) {
        List<Observable<GitHubUser>>  lists = new ArrayList<>();
        Observable<GitHubUser> result = GitHubApiUtils.getInstance().getGitHubApi().login(user,pass);// 利用网络请求拿到的数据
        lists.add(result);
        return lists;
    }

}
