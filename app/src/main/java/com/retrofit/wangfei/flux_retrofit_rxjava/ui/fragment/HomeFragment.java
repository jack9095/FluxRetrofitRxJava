package com.retrofit.wangfei.flux_retrofit_rxjava.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.retrofit.wangfei.flux_retrofit_rxjava.R;
import com.retrofit.wangfei.flux_retrofit_rxjava.ui.base.BaseFragment;
import com.retrofit.wangfei.flux_retrofit_rxjava.util.eventbus.EventCenter;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-04-12
 * Time: 9:57
 * QQ: 929728742
 * Description: 首页
 */
public class HomeFragment extends BaseFragment {

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_home_recycle_view_layout, null);
        return view;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }
}
