package com.retrofit.wangfei.flux_retrofit_rxjava.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.retrofit.wangfei.flux_retrofit_rxjava.R;

/**
 * <Pre>
 *     glide图片加载工具类
 * </Pre>
 *
 * @author fei.wang
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/28 14:07
 */
public class GlideUtil {

    /**
     *
     * @param context    上下文
     * @param url        加载的图片路径
     * @param imageView  把加载好的图片展示出来的ImageView
     */
    public static void loadImage(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)  // 加载的图片路径
                .placeholder(R.mipmap.ic_launcher)  // 正在加载时显示的图片
                .error(R.mipmap.ic_launcher)          // 加载失败时显示的图片
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }
}
