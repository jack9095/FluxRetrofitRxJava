package com.retrofit.wangfei.flux_retrofit_rxjava.persenter;

import android.content.res.TypedArray;
import android.widget.ListView;

import com.retrofit.wangfei.flux_retrofit_rxjava.adapter.DrawerLayoutAdapter;
import com.retrofit.wangfei.flux_retrofit_rxjava.model.DrawerLayoutItem;
import com.retrofit.wangfei.flux_retrofit_rxjava.util.NightModeHelper;

import java.util.ArrayList;

/**
 * User: wangfei
 * Date: 2016-04-12
 * Time: 9:57
 * QQ: 929728742
 * Description:  主页面侧拉框的逻辑
 */
public class MainPersenter {

    private NightModeHelper nightModeHelper;          //日夜模式切换
    private ListView mDrawerMenuListView;                     // 侧拉框布局的ListView
    private ArrayList<DrawerLayoutItem> drawerItems;  // 侧拉框布局的ListView中item显示的数据集合
    private String[] mNavMenuTitles;    // 侧拉框布局的ListView中item标题数组
    //在 Android 自定义 View 的时候，需要使用 TypedArray 来获取 XML layout 中的属性值，使用完之后，需要调用 recyle() 方法将 TypedArray 回收
    private TypedArray mNavMenuIconsTypeArray;       // 侧拉框布局的ListView中item图片数组
    private TypedArray mNavMenuIconTintTypeArray;    // 侧拉框布局的ListView中item图片颜色数组
    private DrawerLayoutAdapter adapter;           // 侧拉框布局的ListView对应适配器
    private int position;
}
