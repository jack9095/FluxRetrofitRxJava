package com.retrofit.wangfei.flux_retrofit_rxjava.action;


import com.retrofit.wangfei.flux_retrofit_rxjava.dispatcher.Dispatcher;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-03-23
 * Time: 9:57
 * Description: 创建Action的基类
 */
public abstract class ActionCreator {

    protected Dispatcher dispatcher;

    public ActionCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
}
