package com.yushilei.myapp.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * @auther by yushilei.
 * @time 2017/4/20-17:20
 * @desc
 */

public class ScrollBehavior extends CoordinatorLayout.Behavior<ImageView> {
    private static final String TAG = "ScrollBehavior";

    public ScrollBehavior() {
    }

    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, ImageView child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, ImageView child, View target, int dx, int dy, int[] consumed) {
        RecyclerView recyclerView = (RecyclerView) target;
        if (dy > 0) {//上滑
            Log.i(TAG, "上滑");
            float top = recyclerView.getY();
            if (top >= 0) {
                ViewCompat.setY(child, child.getY() - dy / 2f);
                consumed[1] = dy;
            }
        } else {//下滑 dy-
            Log.i(TAG, "下滑");
            float top = recyclerView.getY();
            if (top < child.getHeight()) {
                ViewCompat.setY(child, child.getY() - dy / 2f);
                consumed[1] = dy;
            } else {
                ViewCompat.setY(child, 0f);
            }
        }
    }
}
