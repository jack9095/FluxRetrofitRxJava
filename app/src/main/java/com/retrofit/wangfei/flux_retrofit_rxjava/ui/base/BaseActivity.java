//package com.retrofit.wangfei.flux_retrofit_rxjava.ui.base;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.drawable.Drawable;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.util.DisplayMetrics;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//
//import com.retrofit.wangfei.flux_retrofit_rxjava.R;
//import com.retrofit.wangfei.flux_retrofit_rxjava.util.netstatus.NetChangeObserver;
//
//import org.greenrobot.eventbus.EventBus;
//
//import butterknife.ButterKnife;
//
///**
// * Created by Android Studio
// * User: wangfei
// * Date: 2016-04-12
// * Time: 9:57
// * QQ: 929728742
// * Description: Activity的基类
// */
//public abstract class BaseActivity extends AppCompatActivity {
//    /**
//     * Log tag
//     */
//    protected static String TAG_LOG = null;
//    /**
//     * 屏幕参数
//     */
//    protected int mScreenWidth = 0;
//    protected int mScreenHeight = 0;
//    protected float mScreenDensity = 0.0f;
//
//    /**
//     * 上下文
//     */
//    protected Context mContext = null;
//
//    /**
//     * 联网状态
//     */
//    protected NetChangeObserver mNetChangeObserver = null;
//
//    /**
//     * 加载视图控制器
//     */
//    private VaryViewHelperController mVaryViewHelperController = null;
//
//    /**
//     * 两个Activity切换时动画方向的枚举
//     * overridePendingTransition mode
//     */
//    public enum TransitionMode {
//        LEFT, RIGHT, TOP, BOTTOM, SCALE, FADE
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        if (toggleOverridePendingTransition()) {
//            switch (getOverridePendingTransitionMode()) {
//                case LEFT:
//                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
//                    break;
//                case RIGHT:
//                    overridePendingTransition(R.anim.right_in, R.anim.right_out);
//                    break;
//                case TOP:
//                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
//                    break;
//                case BOTTOM:
//                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
//                    break;
//                case SCALE:
//                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
//                    break;
//                case FADE:
//                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                    break;
//            }
//        }
//        super.onCreate(savedInstanceState);
//        // base setup
//        Bundle extras = getIntent().getExtras();
//        if (null != extras) {
//            getBundleExtras(extras);
//        }
//
//        if (isBindEventBusHere()) {
//            EventBus.getDefault().register(this);
//        }
//        SmartBarUtils.hide(getWindow().getDecorView());
//        setTranslucentStatus(isApplyStatusBarTranslucency());
//
//        mContext = this;
//        TAG_LOG = this.getClass().getSimpleName();
//        BaseAppManager.getInstance().addActivity(this);
//
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//
//        mScreenDensity = displayMetrics.density;
//        mScreenHeight = displayMetrics.heightPixels;
//        mScreenWidth = displayMetrics.widthPixels;
//
//        if (getContentViewLayoutID() != 0) {
//            setContentView(getContentViewLayoutID());
//        } else {
//            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
//        }
//
//        mNetChangeObserver = new NetChangeObserver() {
//            @Override
//            public void onNetConnected(NetUtils.NetType type) {
//                super.onNetConnected(type);
//                onNetworkConnected(type);
//            }
//
//            @Override
//            public void onNetDisConnect() {
//                super.onNetDisConnect();
//                onNetworkDisConnected();
//            }
//        };
//
//        NetStateReceiver.registerObserver(mNetChangeObserver);
//
//        initViewsAndEvents();
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            finish();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void setContentView(int layoutResID) {
//        super.setContentView(layoutResID);
//        ButterKnife.bind(this);
//        if (null != getLoadingTargetView()) {
//            mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
//        }
//    }
//
//    @Override
//    public void finish() {
//        super.finish();
//        BaseAppManager.getInstance().removeActivity(this);
//        if (toggleOverridePendingTransition()) {
//            switch (getOverridePendingTransitionMode()) {
//                case LEFT:
//                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
//                    break;
//                case RIGHT:
//                    overridePendingTransition(R.anim.right_in, R.anim.right_out);
//                    break;
//                case TOP:
//                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
//                    break;
//                case BOTTOM:
//                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
//                    break;
//                case SCALE:
//                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
//                    break;
//                case FADE:
//                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                    break;
//            }
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        ButterKnife.unbind(this);
//        NetStateReceiver.removeRegisterObserver(mNetChangeObserver);
//        if (isBindEventBusHere()) {
//            EventBus.getDefault().unregister(this);
//        }
//    }
//
//    /**
//     * get bundle data
//     *
//     * @param extras
//     */
//    protected abstract void getBundleExtras(Bundle extras);
//
//    /**
//     * bind布局资源文件
//     *
//     * @return id of layout resource
//     */
//    protected abstract int getContentViewLayoutID();
//
//    /**
//     * init all views and add events
//     */
//    protected abstract void initViewsAndEvents();
//
//    /**
//     * when event comming
//     *
//     * @param eventCenter
//     */
//    protected abstract void onEventComming(EventCenter eventCenter);
//
//    /**
//     * get loading target view
//     */
//    protected abstract View getLoadingTargetView();
//
//    /**
//     * network connected
//     */
//    protected abstract void onNetworkConnected(NetUtils.NetType type);
//
//    /**
//     * network disconnected
//     */
//    protected abstract void onNetworkDisConnected();
//
//    /**
//     * is applyStatusBarTranslucency
//     *
//     * @return
//     */
//    protected abstract boolean isApplyStatusBarTranslucency();
//
//    /**
//     * is bind eventBus
//     *
//     * @return
//     */
//    protected abstract boolean isBindEventBusHere();
//
//    /**
//     * toggle overridePendingTransition
//     *
//     * @return
//     */
//    protected abstract boolean toggleOverridePendingTransition();
//
//    /**
//     * get the overridePendingTransition mode
//     */
//    protected abstract TransitionMode getOverridePendingTransitionMode();
//
//    /**
//     * startActivity
//     *
//     * @param clazz
//     */
//    protected void readyGo(Class<?> clazz) {
//        Intent intent = new Intent(this, clazz);
//        startActivity(intent);
//    }
//
//    /**
//     * startActivity with bundle
//     *
//     * @param clazz
//     * @param bundle
//     */
//    protected void readyGo(Class<?> clazz, Bundle bundle) {
//        Intent intent = new Intent(this, clazz);
//        if (null != bundle) {
//            intent.putExtras(bundle);
//        }
//        startActivity(intent);
//    }
//
//    /**
//     * startActivity then finish
//     *
//     * @param clazz
//     */
//    protected void readyGoThenKill(Class<?> clazz) {
//        Intent intent = new Intent(this, clazz);
//        startActivity(intent);
//        finish();
//    }
//
//    /**
//     * startActivity with bundle then finish
//     *
//     * @param clazz
//     * @param bundle
//     */
//    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
//        Intent intent = new Intent(this, clazz);
//        if (null != bundle) {
//            intent.putExtras(bundle);
//        }
//        startActivity(intent);
//        finish();
//    }
//
//    /**
//     * startActivityForResult
//     *
//     * @param clazz
//     * @param requestCode
//     */
//    protected void readyGoForResult(Class<?> clazz, int requestCode) {
//        Intent intent = new Intent(this, clazz);
//        startActivityForResult(intent, requestCode);
//    }
//
//    /**
//     * startActivityForResult with bundle
//     *
//     * @param clazz
//     * @param requestCode
//     * @param bundle
//     */
//    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
//        Intent intent = new Intent(this, clazz);
//        if (null != bundle) {
//            intent.putExtras(bundle);
//        }
//        startActivityForResult(intent, requestCode);
//    }
//
//    /**
//     * show toast
//     *
//     * @param msg
//     */
//    protected void showToast(String msg) {
//        //防止遮盖虚拟按键
//        if (null != msg && !CommonUtils.isEmpty(msg)) {
//            Snackbar.make(getLoadingTargetView(), msg, Snackbar.LENGTH_SHORT).show();
//        }
//    }
//
//    /**
//     * toggle show loading
//     *
//     * @param toggle
//     */
//    protected void toggleShowLoading(boolean toggle, String msg) {
//        if (null == mVaryViewHelperController) {
//            throw new IllegalArgumentException("You must return a right target view for loading");
//        }
//
//        if (toggle) {
//            mVaryViewHelperController.showLoading(msg);
//        } else {
//            mVaryViewHelperController.restore();
//        }
//    }
//
//    /**
//     * toggle show empty
//     *
//     * @param toggle
//     */
//    protected void toggleShowEmpty(boolean toggle, String msg, View.OnClickListener onClickListener) {
//        if (null == mVaryViewHelperController) {
//            throw new IllegalArgumentException("You must return a right target view for loading");
//        }
//
//        if (toggle) {
//            mVaryViewHelperController.showEmpty(msg, onClickListener);
//        } else {
//            mVaryViewHelperController.restore();
//        }
//    }
//
//    /**
//     * toggle show error
//     *
//     * @param toggle
//     */
//    protected void toggleShowError(boolean toggle, String msg, View.OnClickListener onClickListener) {
//        if (null == mVaryViewHelperController) {
//            throw new IllegalArgumentException("You must return a right target view for loading");
//        }
//
//        if (toggle) {
//            mVaryViewHelperController.showError(msg, onClickListener);
//        } else {
//            mVaryViewHelperController.restore();
//        }
//    }
//
//    /**
//     * toggle show network error
//     *
//     * @param toggle
//     */
//    protected void toggleNetworkError(boolean toggle, View.OnClickListener onClickListener) {
//        if (null == mVaryViewHelperController) {
//            throw new IllegalArgumentException("You must return a right target view for loading");
//        }
//
//        if (toggle) {
//            mVaryViewHelperController.showNetworkError(onClickListener);
//        } else {
//            mVaryViewHelperController.restore();
//        }
//    }
//
//    public void onEventMainThread(EventCenter eventCenter) {
//        if (null != eventCenter) {
//            onEventComming(eventCenter);
//        }
//    }
//
//    /**
//     * use SytemBarTintManager
//     *
//     * @param tintDrawable
//     */
//    protected void setSystemBarTintDrawable(Drawable tintDrawable) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
//            if (tintDrawable != null) {
//                mTintManager.setStatusBarTintEnabled(true);
//                mTintManager.setTintDrawable(tintDrawable);
//            } else {
//                mTintManager.setStatusBarTintEnabled(false);
//                mTintManager.setTintDrawable(null);
//            }
//        }
//
//    }
//
//    /**
//     * set status bar translucency
//     *
//     * @param on
//     */
//    protected void setTranslucentStatus(boolean on) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window win = getWindow();
//            WindowManager.LayoutParams winParams = win.getAttributes();
//            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//            if (on) {
//                winParams.flags |= bits;
//            } else {
//                winParams.flags &= ~bits;
//            }
//            win.setAttributes(winParams);
//        }
//    }
//}
