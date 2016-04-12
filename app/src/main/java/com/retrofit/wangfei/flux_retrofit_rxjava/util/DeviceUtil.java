package com.retrofit.wangfei.flux_retrofit_rxjava.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * <Pre>
 *     获取设备信息的工具类
 * </Pre>
 *
 * @author fei.wang
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/21 11:41
 */
public class DeviceUtil {
    /**
     * 获取当前应用的版本号
     */
    public static String getVersionName(Context context){
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "0.0.0";
        }
        String versionName = packageInfo.versionName;
        return versionName;
    }
}
