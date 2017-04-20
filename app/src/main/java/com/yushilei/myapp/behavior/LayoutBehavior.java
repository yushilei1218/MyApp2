package com.yushilei.myapp.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.yushilei.myapp.R;

/**
 * @auther by yushilei.
 * @time 2017/4/20-17:06
 * @desc
 */

public class LayoutBehavior extends CoordinatorLayout.Behavior {
    public LayoutBehavior() {
    }

    public LayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == R.id.coordinator2_header;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        int height = dependency.getHeight();
        float y = dependency.getY();
        float childY = height;
        if (y <= 0) {
            childY = childY + 2 * y;
        }
        ViewCompat.setY(child, childY);
        return true;
    }
}
