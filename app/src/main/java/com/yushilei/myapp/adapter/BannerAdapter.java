package com.yushilei.myapp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yushilei.myapp.util.CollectionUtil;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @auther by yushilei.
 * @time 2017/2/21-17:15
 * @desc
 */

public class BannerAdapter extends PagerAdapter {
    List<Integer> data = new LinkedList<>();
    Context context;

    public BannerAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<Integer> data) {
        this.data.clear();
        if (!CollectionUtil.isEmpty(data)) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView img = new ImageView(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        img.setLayoutParams(layoutParams);
        img.setScaleType(ImageView.ScaleType.FIT_START);
        img.setImageResource(data.get(position));
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
