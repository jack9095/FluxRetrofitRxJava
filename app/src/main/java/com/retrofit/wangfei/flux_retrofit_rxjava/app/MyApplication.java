package com.retrofit.wangfei.flux_retrofit_rxjava.app;

import android.app.Application;
import android.os.StrictMode;

import com.retrofit.wangfei.flux_retrofit_rxjava.util.CrashHandler;
import com.retrofit.wangfei.flux_retrofit_rxjava.util.DebugLog;
import com.retrofit.wangfei.flux_retrofit_rxjava.util.ToastUtils;
import com.squareup.leakcanary.LeakCanary;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.GINGERBREAD;

/**
 * 注意onCreate这里不能写太多东西，不然会造成启动延时或很慢的问题
 * User: wangfei
 * Date: 2016-03-23
 * Time: 9:57
 * QQ: 929728742
 * Description:
 */
public class MyApplication extends Application{

    private static MyApplication instance;

    /**注意onCreate这里不能写太多东西，不然会造成启动延时或很慢的问题*/
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this); // 全局捕获异常日志
        ToastUtils.register(this.getApplicationContext());
        DebugLog.isDebug = true;
//        this.enabledStrictMode();  // 初始化检查程序中违例的工具，上线的时候不能用

        //LeakCanary检测OOM
        LeakCanary.install(this);
    }

    public static MyApplication getInstance(){
        return instance;
    }

    /**
     * StrictMode意思为严格模式，是用来检测程序中违例情况的开发者工具。最常用的场景就是检测主线程中本地磁盘和网络读写等耗时的操作
     * 严格模式需要在debug模式开启，不要在release（上线）版本中启用。
     * 使用严格模式，只需要过滤日志就能发现内存泄露
     */
    private void enabledStrictMode() {
        if (SDK_INT >= GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                    .detectAll()  //
                    .penaltyLog() //
                    .penaltyDeath() //
                    .build());
        }
    }
}
