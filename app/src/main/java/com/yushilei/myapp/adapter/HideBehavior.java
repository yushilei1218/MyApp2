package com.yushilei.myapp.adapter;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yushilei.myapp.R;

/**
 * @auther by yushilei.
 * @time 2017/3/10-10:54
 * @desc
 */

public class HideBehavior extends CoordinatorLayout.Behavior<RecyclerView> {
    private static final String TAG = "HideBehavior";

    View dependency;

    public HideBehavior() {
    }

    public HideBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        Log.i(TAG, "layoutDependsOn");
        boolean b = dependency.getId() == R.id.dependency;
        if (b) {
            this.dependency = dependency;
        }
        return b;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {
        Log.i(TAG, "onDependentViewChanged");
        int height = dependency.getHeight();
        int bottom = (int) dependency.getY();
        int i = height - bottom;
        child.setY(height - 2 * i);
        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.i(TAG, "onStartNestedScroll");

        return ViewCompat.SCROLL_AXIS_VERTICAL == nestedScrollAxes;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, int dx, int dy, int[] consumed) {
        Log.i(TAG, "onNestedPreScroll dy=" + dy + " consumed[1]" + consumed[1] + " " + target);
        consumed[1] = dy;
        dependency.setY(dependency.getY() - dy);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i(TAG, "onNestedScroll");
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

}
