package com.retrofit.wangfei.flux_retrofit_rxjava.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Android Studio
 * User: fei.wang
 * Date: 2016-03-15
 * Time: 9:57
 * Description: 保存简单基本数据类型的工具类
 */
public class SharedPreferencesUtils {
    private SharedPreferences mSharedPreferences = null;
    private SharedPreferences.Editor mEditor = null;
    private static SharedPreferencesUtils sharePref = null;

    /**
     * @Title: 创建SharedPreferencesUtils的单例
     * @Description: 在Application全局中注册SP
     * @param PREF_NAME  名称
     * @param context   上下文
     * @return
     */
    public synchronized static SharedPreferencesUtils getInstance(String PREF_NAME, Context context) {
        if (sharePref != null) {
            return sharePref;
        } else {
            return new SharedPreferencesUtils(PREF_NAME, context);
        }

    }

    /**私有化构造函数并创建SharedPreferences*/
    private SharedPreferencesUtils(String PREF_NAME, Context context) {
        mSharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public String getSharePrefString(String key, String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }

    /**获取String类型的值*/
    public String getSharePrefString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    /**获取boolean类型的值，第二个参数一般为false*/
    public boolean getSharePrefBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    public int getSharePrefInteger(String key) {
        return mSharedPreferences.getInt(key, -1);
    }

    public int getSharePrefInteger(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }

    public long getSharePrefLong(String key) {
        return mSharedPreferences.getLong(key, -1);
    }

    public long getSharePrefLong(String key, int value) {
        return mSharedPreferences.getLong(key, -1);
    }

    public float getSharePrefFloat(String key) {
        return mSharedPreferences.getFloat(key, -1);
    }

    /**存储String值，返回true表示存储成功*/
    public boolean putSharePrefString(String key, String value) {
        mEditor.putString(key, value);
        return mEditor.commit();
    }

    /**存储boolean值，返回true表示存储成功*/
    public boolean putSharePrefBoolean(String key, boolean value) {
        mEditor.putBoolean(key, value);
        return mEditor.commit();
    }

    /**存储float值，返回true表示存储成功*/
    public boolean putSharePrefFloat(String key, float value) {
        mEditor.putFloat(key, value);
        return mEditor.commit();
    }

    /**存储long值，返回true表示存储成功*/
    public boolean putSharePrefLong(String key, long value) {
        mEditor.putLong(key, value);
        return mEditor.commit();
    }

    /**存储int值，返回true表示存储成功*/
    public boolean putSharePrefInteger(String key, int value) {
        mEditor.putInt(key, value);
        return mEditor.commit();
    }

    /**根据key移除对应value*/
    public boolean removeKey(String key) {
        mEditor.remove(key);
        return mEditor.commit();
    }

    /**全部清空*/
    public boolean clear() {
        mEditor.clear();
        return mEditor.commit();
    }

}
