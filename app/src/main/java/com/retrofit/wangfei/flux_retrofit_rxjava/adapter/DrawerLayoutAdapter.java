package com.retrofit.wangfei.flux_retrofit_rxjava.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.retrofit.wangfei.flux_retrofit_rxjava.R;
import com.retrofit.wangfei.flux_retrofit_rxjava.model.DrawerLayoutItem;

import java.util.ArrayList;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-04-13
 * Time: 9:57
 * QQ: 929728742
 * Description: 侧拉框页面中ListView对应的适配器
 */
public class DrawerLayoutAdapter extends BaseAdapter{

    private ArrayList<DrawerLayoutItem> drawerItems;  // 侧拉框布局的ListView中item显示的数据集合
    private Context context;

    public DrawerLayoutAdapter(ArrayList<DrawerLayoutItem> drawerItems, Context context) {
        this.drawerItems = drawerItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return drawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return drawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DrawerLayoutItem drawerLayoutItem = drawerItems.get(position);
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.imgIcon = (ImageView) convertView.findViewById(R.id.drawer_icon);
            holder.txtCount = (TextView) convertView.findViewById(R.id.drawer_counter);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.drawer_title);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imgIcon.setImageResource(drawerLayoutItem.icon);
        holder.imgIcon.setColorFilter(context.getResources().getColor(drawerLayoutItem.tint));
        holder.txtTitle.setText(drawerLayoutItem.title);
//        holder.txtCount.setText("这是我的https://github.com/wangfeigit/FluxRetrofitRxJava");
        return convertView;
    }

    class ViewHolder{
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtCount;
    }
}
