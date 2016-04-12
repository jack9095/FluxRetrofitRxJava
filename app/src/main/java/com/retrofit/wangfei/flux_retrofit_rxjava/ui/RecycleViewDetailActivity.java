package com.retrofit.wangfei.flux_retrofit_rxjava.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.retrofit.wangfei.flux_retrofit_rxjava.R;
import com.retrofit.wangfei.flux_retrofit_rxjava.util.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-03-23
 * Time: 9:57
 * QQ: 929728742
 * Description: 点击RecycleView的item跳转的详情页面
 */
public class RecycleViewDetailActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.iv_detail)
    ImageView ivDetail;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.author)
    TextView author;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    /**标题栏最右边图片点击展开后的菜单布局*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    /**标题栏最右边图片点击展开后的菜单布局中控件的点击事件*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                ToastUtils.showShort("关于");
                return true;

            case R.id.action_share:
                ToastUtils.showShort("分享");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.fab})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.fab:
                Snackbar.make(v, "this Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }
}
