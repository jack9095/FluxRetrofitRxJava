package com.retrofit.wangfei.flux_retrofit_rxjava.action;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-03-23
 * Time: 9:57
 * Description: 登陆action
 */
public class LoginAction extends Action {
    // 对应的事件类型
    public static final String LOGIN_START = "loadingStart";
    public static final String LOGIN_VIEW = "initViewData";
    public static final String LOGIN_ERROR = "fetchDataError";

    public LoginAction(String type, Object data) {
        super(type, data);
    }
}
