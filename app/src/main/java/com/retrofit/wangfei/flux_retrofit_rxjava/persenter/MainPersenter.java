package com.retrofit.wangfei.flux_retrofit_rxjava.persenter;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.retrofit.wangfei.flux_retrofit_rxjava.R;
import com.retrofit.wangfei.flux_retrofit_rxjava.adapter.DrawerLayoutAdapter;
import com.retrofit.wangfei.flux_retrofit_rxjava.model.DrawerLayoutItem;
import com.retrofit.wangfei.flux_retrofit_rxjava.ui.activity.MainActivity;
import com.retrofit.wangfei.flux_retrofit_rxjava.ui.activity.RecycleViewDetailActivity;
import com.retrofit.wangfei.flux_retrofit_rxjava.ui.base.BaseWebActivity;
import com.retrofit.wangfei.flux_retrofit_rxjava.ui.fragment.CollectFragment;
import com.retrofit.wangfei.flux_retrofit_rxjava.ui.fragment.DraftFragment;
import com.retrofit.wangfei.flux_retrofit_rxjava.ui.fragment.FindFragment;
import com.retrofit.wangfei.flux_retrofit_rxjava.ui.fragment.FollowFragment;
import com.retrofit.wangfei.flux_retrofit_rxjava.ui.fragment.HomeFragment;
import com.retrofit.wangfei.flux_retrofit_rxjava.ui.fragment.QuestionFragment;
import com.retrofit.wangfei.flux_retrofit_rxjava.util.BaseUtil;
import com.retrofit.wangfei.flux_retrofit_rxjava.util.DebugLog;
import com.retrofit.wangfei.flux_retrofit_rxjava.util.GlideUtil;
import com.retrofit.wangfei.flux_retrofit_rxjava.util.NightModeHelper;

import java.util.ArrayList;

import butterknife.OnClick;

/**
 * User: wangfei
 * Date: 2016-04-12
 * Time: 9:57
 * QQ: 929728742
 * Description:  主页面侧拉框的逻辑
 */
public class MainPersenter extends BasePersenter implements View.OnClickListener{

    private NightModeHelper nightModeHelper;          //日夜模式切换
    private ListView mDrawerMenuListView;                     // 侧拉框布局的ListView
    public ArrayList<DrawerLayoutItem> drawerItems;  // 侧拉框布局的ListView中item显示的数据集合
    private String[] mNavMenuTitles;    // 侧拉框布局的ListView中item标题数组
    //在 Android 自定义 View 的时候，需要使用 TypedArray 来获取 XML layout 中的属性值，使用完之后，需要调用 recyle() 方法将 TypedArray 回收
    private TypedArray mNavMenuIconsTypeArray;       // 侧拉框布局的ListView中item图片数组
    private TypedArray mNavMenuIconTintTypeArray;    // 侧拉框布局的ListView中item图片颜色数组
    private DrawerLayoutAdapter adapter;           // 侧拉框布局的ListView对应适配器
    public int position;

    private MainActivity mMainActivity;

    public MainPersenter(MainActivity mMainActivity) {
        this.mMainActivity = mMainActivity;
    }

    @Override
    public void initialize() {
        mMainActivity.setSystemBarTintDrawable("#24b7a4");
        initView();
        setupToolbar();
        setUpDrawer();
    }

    /**初始化控件*/
    private void initView(){
        nightModeHelper = new NightModeHelper(mMainActivity);
        GlideUtil.loadImage(mMainActivity,"http://fxblog.oss-cn-beijing.aliyuncs.com/avatar_img.png",mMainActivity.userImg);
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
        mMainActivity.setSupportActionBar(mMainActivity.toolbar);
        return mMainActivity.getSupportActionBar();
    }

    /**打开侧拉框*/
    public void openDrawer() {
        if (mMainActivity.drawerLayout == null)
            return;
        mMainActivity.drawerLayout.openDrawer(GravityCompat.START);
    }

    /**关闭侧拉框*/
    public void closeDrawer() {
        if (mMainActivity.drawerLayout == null)
            return;
        mMainActivity.drawerLayout.closeDrawer(GravityCompat.START);
    }

    /**设置侧拉框*/
    private void setUpDrawer() {
        if (mMainActivity.drawerLayout == null) {
            // current activity does not have a drawer.
            return;
        }
        mDrawerMenuListView = (ListView) mMainActivity.findViewById(R.id.left_menu);  // 侧拉框ListView
        mNavMenuTitles = mMainActivity.getResources().getStringArray(R.array.nav_drawer_items);  // 侧拉框ListView对应Item标题的数组
        mNavMenuIconsTypeArray = mMainActivity.getResources().obtainTypedArray(R.array.nav_drawer_icons);// 侧拉框ListView对应Item图片的集合
        mNavMenuIconTintTypeArray = mMainActivity.getResources().obtainTypedArray(R.array.nav_drawer_tint);// 侧拉框ListView对应Item图片颜色的集合

        drawerItems = new ArrayList<>();  // 存储侧拉框ListView对应Item数据（DrawerLayoutItem）的集合
        for (int i = 0; i < mNavMenuTitles.length; i++) {  // 遍历存储侧拉框ListView对应Item数据的数组
            drawerItems.add(new DrawerLayoutItem(mNavMenuTitles[i], mNavMenuIconsTypeArray
                    .getResourceId(i, -1), mNavMenuIconTintTypeArray.getResourceId(i, -1))); // 给侧拉框ListView对应Item赋值
        }
        mNavMenuIconsTypeArray.recycle(); // 用完及时回收
        adapter = new DrawerLayoutAdapter(drawerItems,mMainActivity);
        mDrawerMenuListView.setAdapter(adapter);
        mDrawerMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!BaseUtil.isEmpty(drawerItems, position)) {
                    DrawerLayoutItem drawerLayoutItem = drawerItems.get(position);
                    if (drawerLayoutItem != null) {
                        selectItem(position, drawerLayoutItem.title);
                    }
                }
            }
        });
        selectItem(0, drawerItems.get(0).title);
    }

    /**
     * 左侧drawer选择事件
     *
     * @param position
     * @param title
     */
    public void selectItem(int position, String title) {
        Fragment fragment = null;
        this.position = position;
        switch (position) {
            case 0:
                //首页
                fragment = new HomeFragment();
                break;
            case 1:
                //发现
                fragment = new FindFragment();
                break;
            case 2:
                //关注
                fragment = new FollowFragment();
                break;
            case 3:
                //收藏
                fragment = new CollectFragment();
                break;
            case 4:
                //草稿
                fragment = new DraftFragment();
                break;
            case 5:
                //提问
                fragment = new QuestionFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            mMainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
            mMainActivity.setTitle(title);  //Activity系统自带的方法
            closeDrawer();
        } else {
            DebugLog.e("fragment创建异常");
        }
    }

    /**
     * 主题切换
     *
     * @param view
     */
    private void changeTheme(final View view) {
        nightModeHelper.toggle();
    }

    @OnClick({R.id.user_img, R.id.tv_setting, R.id.tv_change_theme})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_img:
                closeDrawer();
                mMainActivity.readyGo(RecycleViewDetailActivity.class);
                break;
            case R.id.tv_setting:

                break;
            case R.id.tv_change_theme:
                changeTheme(mMainActivity.homeLayout);
                break;
        }
    }


}
