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
public class MainActivity extends BaseActivity implements View.OnClickListener{

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


    private NightModeHelper nightModeHelper;          //日夜模式切换
    private ListView mDrawerMenuListView;                     // 侧拉框布局的ListView
    private ArrayList<DrawerLayoutItem> drawerItems;  // 侧拉框布局的ListView中item显示的数据集合
    private String[] mNavMenuTitles;    // 侧拉框布局的ListView中item标题数组
    //在 Android 自定义 View 的时候，需要使用 TypedArray 来获取 XML layout 中的属性值，使用完之后，需要调用 recyle() 方法将 TypedArray 回收
    private TypedArray mNavMenuIconsTypeArray;       // 侧拉框布局的ListView中item图片数组
    private TypedArray mNavMenuIconTintTypeArray;    // 侧拉框布局的ListView中item图片颜色数组
    private DrawerLayoutAdapter adapter;           // 侧拉框布局的ListView对应适配器
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        setupToolbar();
        setUpDrawer();
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

    /**设置侧拉框*/
    private void setUpDrawer() {
        if (drawerLayout == null) {
            // current activity does not have a drawer.
            return;
        }
        mDrawerMenuListView = (ListView) findViewById(R.id.left_menu);  // 侧拉框ListView
        mNavMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);  // 侧拉框ListView对应Item标题的数组
        mNavMenuIconsTypeArray = getResources().obtainTypedArray(R.array.nav_drawer_icons);// 侧拉框ListView对应Item图片的集合
        mNavMenuIconTintTypeArray = getResources().obtainTypedArray(R.array.nav_drawer_tint);// 侧拉框ListView对应Item图片颜色的集合

        drawerItems = new ArrayList<>();  // 存储侧拉框ListView对应Item数据（DrawerLayoutItem）的集合
        for (int i = 0; i < mNavMenuTitles.length; i++) {  // 遍历存储侧拉框ListView对应Item数据的数组
            drawerItems.add(new DrawerLayoutItem(mNavMenuTitles[i], mNavMenuIconsTypeArray
                    .getResourceId(i, -1), mNavMenuIconTintTypeArray.getResourceId(i, -1))); // 给侧拉框ListView对应Item赋值
        }
        mNavMenuIconsTypeArray.recycle(); // 用完及时回收
        adapter = new DrawerLayoutAdapter(drawerItems,this);
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
            getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
            setTitle(title);  //Activity系统自带的方法
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
                readyGo(RecycleViewDetailActivity.class);
                break;
            case R.id.tv_setting:

                break;
            case R.id.tv_change_theme:
                changeTheme(homeLayout);
                break;
        }
    }

    /**android中的后退键——onBackPressed()的使用 */
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) { // 返回的时候如果侧拉框是打开状态，就关闭掉
            closeDrawer();
            return;
        }

        if (position != 0) {
            selectItem(0, drawerItems.get(0).title);  // 默认为第一个item
        } else {
            super.onBackPressed();
        }
    }

}
