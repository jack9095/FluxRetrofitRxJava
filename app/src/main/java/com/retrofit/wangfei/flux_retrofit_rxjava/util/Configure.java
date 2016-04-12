package com.retrofit.wangfei.flux_retrofit_rxjava.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * 一些常用的系统配置信息,获取自定义的配置信息 类
 *
 */
public class Configure {

	/**屏幕的高*/
	public static int screenHeight = 0;
	/**屏幕的宽*/
	public static int screenWidth = 0;
	/**屏幕密度分辨率*/
	public static float screenDensity = 0;
	/**每英寸多少个点，我的理解是每英寸多少个dip*/
	public static int densityDpi = 0;
	/**获取当前系统的android版本号*/
	public static int version = Integer.valueOf(android.os.Build.VERSION.SDK_INT);

	public static void init(Activity context) {
		if (screenDensity == 0 || screenWidth == 0 || screenHeight == 0) {
			DisplayMetrics dm = new DisplayMetrics();
			context.getWindowManager().getDefaultDisplay().getMetrics(dm);
			Configure.screenDensity = dm.density;
			Configure.screenHeight = dm.heightPixels;
			Configure.screenWidth = dm.widthPixels;
			Configure.densityDpi = dm.densityDpi;
		}
		Log.i("SCREEN CONFIGURE", "screenHeight:" + screenHeight
				+ ";screenWidth:" + screenWidth + ";screenDensity:"
				+ screenDensity + ";densityDpi:" + densityDpi + ";版本号：" + version);
	}

}