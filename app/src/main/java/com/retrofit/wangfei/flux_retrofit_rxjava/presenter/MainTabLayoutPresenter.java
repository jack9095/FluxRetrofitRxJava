package com.retrofit.wangfei.flux_retrofit_rxjava.presenter;

import com.retrofit.wangfei.flux_retrofit_rxjava.ui.activity.MainActivity;

/**
 * User: wangfei
 * Date: 2016-04-12
 * Time: 9:57
 * QQ: 929728742
 * Description:  主页面Tab的逻辑
 */
public class MainTabLayoutPresenter extends BasePresenter {

    private MainActivity mainActivity;

    /**主页面底部Tab的逻辑*/
    public MainTabLayoutPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void initialize() {

    }
}
