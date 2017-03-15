package com.yushilei.myapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @auther by yushilei.
 * @time 2017/3/15-10:53
 * @desc
 */

public class LayoutView extends ViewGroup {

    private int scaledTouchSlop;

    public LayoutView(Context context) {
        super(context);
    }

    public LayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View child = getChildAt(0);
        if (child != null) {
            child.layout(0, 0, child.getMeasuredWidth(), child.getMeasuredHeight());
        }
    }

    float lx;
    float ly;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        boolean is = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(x - lx) >= scaledTouchSlop || Math.abs(y - ly) >= scaledTouchSlop) {
                    is = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        lx = x;
        ly = y;
        return is;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE: {
                View child = getChildAt(0);
                if (child != null) {
                    scrollBy((int) (lx - x), (int) ly - y);
                    if (child instanceof TextView) {
                        String text = "top=" + child.getTop() + " y=" + child.getY();
                        ((TextView) child).setText(text);
                    }
                }
            }
            break;
            case MotionEvent.ACTION_UP:
                break;
        }
        lx = x;
        ly = y;
        return true;
    }


}
