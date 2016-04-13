package com.retrofit.wangfei.flux_retrofit_rxjava.ui.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
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
import com.retrofit.wangfei.flux_retrofit_rxjava.adapter.DrawerLayoutAdapter;
import com.retrofit.wangfei.flux_retrofit_rxjava.model.DrawerLayoutItem;
import com.retrofit.wangfei.flux_retrofit_rxjava.persenter.MainPersenter;
import com.retrofit.wangfei.flux_retrofit_rxjava.ui.base.BaseActivity;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-04-12
 * Time: 9:57
 * QQ: 929728742
 * Description: 主页面
 */
public class MainActivity extends BaseActivity{

    @Bind(R.id.toolbar)
    public Toolbar toolbar;
    @Bind(R.id.content)
    public FrameLayout content;
    @Bind(R.id.user_img)
    public ImageView userImg;
    @Bind(R.id.tv_name)
    public TextView tvName;
    @Bind(R.id.tv_autograph)
    public TextView tvAutograph;
    @Bind(R.id.tv_change_theme)
    public TextView tvChangeTheme;
    @Bind(R.id.tv_setting)
    public TextView tvSetting;
    @Bind(R.id.nv_drawer_layout)
    public LinearLayout nvDrawerLayout;
    @Bind(R.id.drawer_layout)
    public DrawerLayout drawerLayout;
    @Bind(R.id.mengban_view)
    public ImageView mengbanView;
    @Bind(R.id.home_layout)
    public FrameLayout homeLayout;

    private MainPersenter mMainPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainPersenter = new MainPersenter(this);
        ButterKnife.bind(this);
        mMainPersenter.initialize();
    }

    /**标题栏最右边图片点击展开后的菜单布局*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    /**标题栏最右边图片点击展开后的菜单布局中控件的点击事件*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mMainPersenter.openDrawer();
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

    /**android中的后退键——onBackPressed()的使用 */
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) { // 返回的时候如果侧拉框是打开状态，就关闭掉
            mMainPersenter.closeDrawer();
            return;
        }

        if (mMainPersenter.position != 0) {
            mMainPersenter.selectItem(0, mMainPersenter.drawerItems.get(0).title);  // 默认为第一个item
        } else {
            super.onBackPressed();
        }
    }

}
