package com.retrofit.wangfei.flux_retrofit_rxjava.action;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-03-15
 * Time: 9:57
 * Description: Action的基类
 */
public abstract class Action {
    private String type;     // 事件类型
    private String data;     // 数据

    public Action(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }
}
