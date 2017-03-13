package com.yushilei.myapp.adapter;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @auther by yushilei.
 * @time 2017/3/13-09:57
 * @desc
 */

public class HeaderBehavior extends CoordinatorLayout.Behavior<View> {
    private String TAG = "HeaderBehavior";

    public HeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {

        Log.i(TAG, "onStartNestedScroll" + child + " " + directTargetChild + " " + target);
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {

        if (target instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) target;
            if (dy > 0) {//上滑
                View childAt = recyclerView.getChildAt(0);
                int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (position == 0 && childAt.getTop() == 0 && -child.getY() < child.getHeight()) {
                    consumed[1] = dy;
                    float y = child.getY();
                    float offest = -(y - dy) >= child.getHeight() ? -child.getHeight() : y - dy;
                    ViewCompat.setY(child, offest);
                }
            } else {//下滑 dy-
                float y = child.getY();
                if (y < 0) {
                    consumed[1] = dy;
                    float v = y - dy >= 0 ? 0 : y - dy;
                    ViewCompat.setY(child, v);
                }


            }
        }
        Log.i(TAG, "onNestedPreScroll " + child + " " + " target" + target + " dy=" + dy);
    }
}
