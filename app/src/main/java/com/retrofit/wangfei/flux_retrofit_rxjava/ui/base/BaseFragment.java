package com.retrofit.wangfei.flux_retrofit_rxjava.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.retrofit.wangfei.flux_retrofit_rxjava.util.CommonUtils;
import com.retrofit.wangfei.flux_retrofit_rxjava.util.eventbus.EventCenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-04-12
 * Time: 9:57
 * QQ: 929728742
 * Description: Fragment基类
 */
public abstract class BaseFragment extends Fragment{

    protected Context context;
    private View view;
    private boolean isPrepared; // 标志位，标志已经初始化完成
    private boolean isFirstResume = true; // 是否第一次可见
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;

    /**fragment创建的时候调用*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getActivity();
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
    }

    /**实现该方法即可实现可见再加载。不可见不加载的效果，setUserVisibleHint是在onCreateView之前调用的*/
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {   // 第一次可见
                isFirstVisible = false;
                initPrepare();  // 加载数据
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {  // 第一次不可见
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }
    }

    /**当创建fragment的UI被初始化时调用,onCreateView返回的就是fragment要显示的view*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = initView(inflater);
       return view;
    }

    /**onViewCreated在onCreateView执行完后立即执行*/
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initViewsAndEvents();
    }

    /**这个方法是activity的onCreate()方法执行完返回时调用，适用于Fragment加载数据*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {  // 如果是第一次可见，就设为false
            isFirstResume = false;
            return;
        }
        // 判断当前fragment是否显示
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) { //判断当前fragment是否显示
            onUserInvisible();
        }
    }

    /**销毁Fragment创建的UIView*/
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**初始化预加载的数据*/
    private synchronized void initPrepare() {
        if (isPrepared) {    // 标志位，标志已经初始化完成
            onFirstUserVisible();
        } else {
            isPrepared = true; // 如果不是，就置为true
        }
    }

    /**
     * 这种方法就像Fragment的生命周期方法onResume()
     */
    protected abstract void onUserVisible();

    /**
     * 这种方法就像fragment的生命周期方法onPause()
     */
    protected abstract void onUserInvisible();

    /**
     * 当Fragment是可见的第一次,我们可以做一些初始化的工作或刷新数据只有一次
     * 第一次进来数据刷新的时候可以在这个方法中操作
     */
    protected abstract void onFirstUserVisible();

    /**
     * init all views and add events
     */
    protected abstract void initViewsAndEvents();

    /**
     * bind layout resource file
     * @return id of layout resource
     */
    protected abstract View initView(LayoutInflater inflater);

    /**
     * is bind eventBus
     * @return
     */
    protected abstract boolean isBindEventBusHere();

    /**
     * when event comming
     * @param eventCenter
     */
    protected abstract void onEventComming(EventCenter eventCenter);




    /**
     * 当fragment 第一次不可见，我们不建议做点什么
     */
    private void onFirstUserInvisible() {
        // here we do not recommend do something  我们不建议做点什么
    }

    /**
     * startActivity
     *
     * @param clazz
     */
    protected void startActivity(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    /**
     * show toast
     *
     * @param msg
     */
    protected void showToast(String msg) {
        if (null != msg && !CommonUtils.isEmpty(msg)) {
            Snackbar.make(((Activity) context).getWindow().getDecorView(), msg, Snackbar.LENGTH_SHORT).show();
        }
    }

    public void onEventMainThread(EventCenter eventCenter) {
        if (null != eventCenter) {
            onEventComming(eventCenter);
        }
    }
}
