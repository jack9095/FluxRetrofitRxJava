package com.retrofit.wangfei.flux_retrofit_rxjava.ui;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.retrofit.wangfei.flux_retrofit_rxjava.R;
import com.retrofit.wangfei.flux_retrofit_rxjava.ui.base.BaseActivity;
import com.retrofit.wangfei.flux_retrofit_rxjava.ui.base.BaseWebActivity;
import com.retrofit.wangfei.flux_retrofit_rxjava.util.BaseUtil;
import com.retrofit.wangfei.flux_retrofit_rxjava.util.GlideUtil;
import com.retrofit.wangfei.flux_retrofit_rxjava.util.NightModeHelper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-04-12
 * Time: 9:57
 * QQ: 929728742
 * Description: 主页面
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.content)
    FrameLayout content;
    @Bind(R.id.user_img)
    ImageView userImg;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_autograph)
    TextView tvAutograph;
    @Bind(R.id.left_menu)
    ListView leftMenu;
    @Bind(R.id.tv_change_theme)
    TextView tvChangeTheme;
    @Bind(R.id.tv_setting)
    TextView tvSetting;
    @Bind(R.id.nv_drawer_layout)
    LinearLayout nvDrawerLayout;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.mengban_view)
    ImageView mengbanView;
    @Bind(R.id.home_layout)
    FrameLayout homeLayout;

    //日夜模式切换
    private NightModeHelper nightModeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        setupToolbar();
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    /**初始化控件*/
    private void initView(){
        nightModeHelper = new NightModeHelper(this);
        GlideUtil.loadImage(MainActivity.this,"http://fxblog.oss-cn-beijing.aliyuncs.com/avatar_img.png",userImg);
    }

    /**设置Toolbar功能属性*/
    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    /**获取Toolbar*/
    protected ActionBar getActionBarToolbar() {
//        if (toolbar == null) {
//            toolbar = (Toolbar) findViewById(R.id.toolbar);
//            if (toolbar != null) {
//                setSupportActionBar(toolbar);
//            }
//        }
        setSupportActionBar(toolbar);
        return getSupportActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                openDrawer();
                return true;
            case R.id.action_settings:
                Bundle extras = new Bundle();
                extras.putString(BaseWebActivity.BUNDLE_KEY_URL, "https://github.com/fangx");
                extras.putString(BaseWebActivity.BUNDLE_KEY_TITLE, "关于我");
                readyGo(BaseWebActivity.class, extras);
                break;
            case R.id.action_share:
                BaseUtil.share(this, "分享项目地址", "https://github.com/fangx/ZhiHuMVP");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**打开侧拉框*/
    protected void openDrawer() {
        if (drawerLayout == null)
            return;
        drawerLayout.openDrawer(GravityCompat.START);
    }

    /**关闭侧拉框*/
    protected void closeDrawer() {
        if (drawerLayout == null)
            return;
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void setUpDrawer() {
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout == null) {
            // current activity does not have a drawer.
            return;
        }
//        mDrawerMenu = (ListView) findViewById(R.id.left_menu);
//        mNavMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
//        // nav drawer icons from resources
//        mNavMenuIconsTypeArray = getResources()
//                .obtainTypedArray(R.array.nav_drawer_icons);
//        mNavMenuIconTintTypeArray = getResources()
//                .obtainTypedArray(R.array.nav_drawer_tint);
//        mNavDrawerItems = new ArrayList<NavDrawerItem>();
//        for (int i = 0; i < mNavMenuTitles.length; i++) {
//            mNavDrawerItems.add(new NavDrawerItem(mNavMenuTitles[i], mNavMenuIconsTypeArray
//                    .getResourceId(i, -1), mNavMenuIconTintTypeArray.getResourceId(i, -1)));
//        }
//        mNavMenuIconsTypeArray.recycle();
//        // setting the nav drawer list adapter
//        mAdapter = new NavDrawerListAdapter(this,
//                mNavDrawerItems);
//        mDrawerMenu.setAdapter(mAdapter);
//        mDrawerMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if (!BaseUtil.isEmpty(mNavDrawerItems, i)) {
//                    NavDrawerItem navDrawerItem = mNavDrawerItems.get(i);
//                    if (navDrawerItem != null) {
//                        selectItem(i, navDrawerItem.getTitle());
//                    }
//                }
//            }
//        });
//
//
//        selectItem(0, mNavDrawerItems.get(0).getTitle());
    }

}
