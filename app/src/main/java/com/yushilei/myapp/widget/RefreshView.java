package com.yushilei.myapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

/**
 * @auther by yushilei.
 * @time 2017/2/28-10:00
 * @desc
 */

public class RefreshView extends FrameLayout {

    private int touchSlop;

    public RefreshView(Context context) {
        super(context);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    float mLastX;
    float mLastY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        boolean intercepted = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float diffY = y - mLastY;
                float diffX = x - mLastX;
                if (refreshListener != null && diffY > 0 && diffY > Math.abs(diffX) && diffY >= touchSlop) {
                    intercepted = refreshListener.canRefresh();
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mLastX = x;
        mLastY = y;
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float diffY = y - mLastY;
                if (getScrollY() - diffY <= 0) {
                    scrollTo(0, (int) (getScrollY() - diffY));
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    private RefreshHelperListener refreshListener;

    public void setRefreshListener(RefreshHelperListener refreshListener) {
        this.refreshListener = refreshListener;
    }

    public interface RefreshHelperListener {
        boolean canRefresh();
    }
}
