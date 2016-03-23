package com.retrofit.wangfei.flux_retrofit_rxjava.dispatcher;

import com.retrofit.wangfei.flux_retrofit_rxjava.action.Action;
import com.retrofit.wangfei.flux_retrofit_rxjava.store.Store;

import java.util.ArrayList;
import java.util.List;

/**
* Created by Android Studio
* User: wangfei
* Date: 2016-03-15
* Time: 9:57
* Description: 创建Dispatcher的调度类
*/
public class Dispatcher {

    private static Dispatcher mDispatcher;
    private List<Store> stores = new ArrayList<>();

    private Dispatcher(){}

    public static Dispatcher getInstance(){
        if ( mDispatcher == null) {
            mDispatcher = new Dispatcher();
        }
        return mDispatcher;
    }

    public void register(Store store){stores.add(store);}

    public void unRegister(Store store){stores.remove(store);}

    public void dispatcher(Action action){
        post(action);
    }

    private void post(Action action) {
        for (Store store : stores) {
             store.onAction(action);
        }
    }
}
