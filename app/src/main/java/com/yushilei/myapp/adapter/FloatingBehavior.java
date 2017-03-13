package com.yushilei.myapp.adapter;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

/**
 * @auther by yushilei.
 * @time 2017/3/13-15:47
 * @desc
 */

public class FloatingBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {
    public FloatingBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        float y = dependency.getY();
        int height = dependency.getHeight();
        int width = dependency.getWidth();
        child.setY(y + height - child.getMeasuredHeight() / 2);
        child.setX(width * 0.7f);
        if (Math.abs(y)>0.6*height){
            child.setVisibility(View.GONE);
        }else {
            child.setVisibility(View.VISIBLE);
        }

        return true;
    }
}
