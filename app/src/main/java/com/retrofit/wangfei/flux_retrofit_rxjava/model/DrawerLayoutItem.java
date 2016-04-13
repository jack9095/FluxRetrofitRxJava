package com.retrofit.wangfei.flux_retrofit_rxjava.model;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-04-13
 * Time: 9:57
 * QQ: 929728742
 * Description: 侧拉框页面中ListView对应的item实体类
 */
public class DrawerLayoutItem {
    public String title;   // 标题
    public int icon;       // 图片
    public int tint;       //图片颜色
    public String count = "0";
    // boolean to set visiblity of the counter
    public boolean isCounterVisible = false;

    public DrawerLayoutItem(String title, int icon ,int tint){
        this.title = title;
        this.icon = icon;
        this.tint = tint;
    }
}
