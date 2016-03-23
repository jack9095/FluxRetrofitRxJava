package com.retrofit.wangfei.flux_retrofit_rxjava.store;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-03-14
 * Time: 12:26
 * Description: 泛型T表示不同类型的Action
 */
public abstract class Store<T> {

    /**注册EventBus
     * @param subscriber*/
    public void register(Object subscriber){ EventBus.getDefault().register(subscriber);}

    /**反注册EventBus*/
    public void unRegister(Object subscriber){EventBus.getDefault().unregister(subscriber);}

    /**处理收到的不同事件*/
    public abstract void onAction(T action);

    /**
     * 把EventBus发送的event对象抽象化，让每一个继承Store<T>的类实现changeEvent()方法，
     * 并返回这个对象，通过EventBus.getDefault().post(storeChangeEvent);发送出去
     * @return StoreChangeEvent
     */
    protected abstract StoreChangeEvent changeEvent();

    /**发送更新UI的事件给view*/
    public void post(){
        StoreChangeEvent storeChangeEvent = changeEvent();
        if (storeChangeEvent != null) {
            EventBus.getDefault().post(storeChangeEvent); // 发送event对象（event对象中包含要更新UI的内容）
        }
    }

    /**EventBus发送的event对象*/
    public class StoreChangeEvent{

    }
}
