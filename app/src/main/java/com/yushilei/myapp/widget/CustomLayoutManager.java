package com.yushilei.myapp.widget;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @auther by yushilei.
 * @time 2017/3/15-16:23
 * @desc
 */

public class CustomLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
