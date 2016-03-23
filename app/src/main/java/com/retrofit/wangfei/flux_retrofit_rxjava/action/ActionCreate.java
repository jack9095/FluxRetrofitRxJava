package com.retrofit.wangfei.flux_retrofit_rxjava.action;


import com.retrofit.wangfei.flux_retrofit_rxjava.dispatcher.Dispatcher;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-03-15
 * Time: 9:57
 * Description: 创建Action的基类
 */
public abstract class ActionCreate {

    protected Dispatcher dispatcher;

    public ActionCreate(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
}
