package com.retrofit.wangfei.flux_retrofit_rxjava.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.retrofit.wangfei.flux_retrofit_rxjava.R;
import com.retrofit.wangfei.flux_retrofit_rxjava.util.CommonUtils;
import com.retrofit.wangfei.flux_retrofit_rxjava.view.BrowserLayout;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/12.
 */
public class BaseWebActivity extends BaseActivity {

    public static final String BUNDLE_KEY_URL = "BUNDLE_KEY_URL";
    public static final String BUNDLE_KEY_TITLE = "BUNDLE_KEY_TITLE";
    public static final String BUNDLE_KEY_SHOW_BOTTOM_BAR = "BUNDLE_KEY_SHOW_BOTTOM_BAR";

    private String mWebUrl = null;
    private String mWebTitle = null;
    private boolean isShowBottomBar = true;

    private Toolbar mToolBar = null;
    private BrowserLayout mBrowserLayout = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_web);
        getBundleExtras();
        initViewsAndEvents();
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    /**获取WebView页面标题和链接*/
    protected void getBundleExtras() {
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            mWebTitle = extras.getString(BUNDLE_KEY_TITLE);
            mWebUrl = extras.getString(BUNDLE_KEY_URL);
            isShowBottomBar = extras.getBoolean(BUNDLE_KEY_SHOW_BOTTOM_BAR);
        }
    }

    protected void initViewsAndEvents() {
        setSystemBarTintDrawable(getResources().getDrawable(R.drawable.sr_primary));

        mToolBar = ButterKnife.findById(this, R.id.common_toolbar);
        mToolBar.setVisibility(View.GONE);
        mBrowserLayout = ButterKnife.findById(this, R.id.common_web_browser_layout);

//        if (null != mToolBar) {
//            setSupportActionBar(mToolBar);
//            getSupportActionBar().setHomeButtonEnabled(true);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }

        if (!CommonUtils.isEmpty(mWebTitle)) {
            setTitle(mWebTitle);
        } else {
            setTitle("网页");
        }

        if (!CommonUtils.isEmpty(mWebUrl)) {
            mBrowserLayout.loadUrl(mWebUrl);
        } else {
            showToast("获取URL地址失败");
        }

        if (!isShowBottomBar) {
            mBrowserLayout.hideBrowserController();
        } else {
            mBrowserLayout.showBrowserController();
        }
    }
}
