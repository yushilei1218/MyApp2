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
        child.setY(dependency.getY() + dependency.getHeight());
        return true;
    }
}
