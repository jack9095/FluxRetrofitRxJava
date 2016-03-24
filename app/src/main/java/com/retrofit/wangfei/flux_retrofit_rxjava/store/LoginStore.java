package com.retrofit.wangfei.flux_retrofit_rxjava.store;

import com.retrofit.wangfei.flux_retrofit_rxjava.action.LoginAction;
import com.retrofit.wangfei.flux_retrofit_rxjava.model.GitHubUser;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-03-23
 * Time: 9:57
 * Description:
 */
public class LoginStore extends Store<LoginAction> {

    private static LoginStore userStore;
    private GitHubUser user;
    private Throwable throwable;
    private StoreChangeEvent changeEvent;

    public static LoginStore getInstance() {
        if (userStore == null) {
            synchronized (LoginStore.class) {
            }
            userStore = new LoginStore();
        }
        return userStore;
    }

    protected LoginStore() {
        user = new GitHubUser();
    }

    public void startLoading() {

    }

    public GitHubUser getUserList() {
        return user;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    @Subscribe
    @Override
    public void onAction(final LoginAction action) {
        switch (action.getType()) {
            case LoginAction.LOGIN_START:
                changeEvent = new LoadingStartChangeEvent();
                post();
                break;
            case LoginAction.LOGIN_VIEW:
                changeEvent = new InitViewChangeEvent();
                user = (GitHubUser) action.getData();
                post();
                break;
            case LoginAction.LOGIN_ERROR:
                changeEvent = new ErrorChangeEvent();
                throwable = (Throwable) action.getData();
                post();
                break;
        }
    }

    @Override
    protected StoreChangeEvent changeEvent() {
        return changeEvent;
    }

    public class LoadingStartChangeEvent extends StoreChangeEvent {}

    public class InitViewChangeEvent extends StoreChangeEvent {}

    public class ErrorChangeEvent extends StoreChangeEvent {}
}
