package com.retrofit.wangfei.flux_retrofit_rxjava.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.retrofit.wangfei.flux_retrofit_rxjava.R;
import com.retrofit.wangfei.flux_retrofit_rxjava.ui.base.BaseFragment;
import com.retrofit.wangfei.flux_retrofit_rxjava.util.eventbus.EventCenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-04-12
 * Time: 9:57
 * QQ: 929728742
 * Description: 首页
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.recycle)
    RecyclerView recycle;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
