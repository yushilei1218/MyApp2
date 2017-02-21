package com.yushilei.myapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Scroller;

import com.yushilei.myapp.R;

/**
 * @auther by yushilei.
 * @time 2017/2/21-15:25
 * @desc
 */

public class DragView extends FrameLayout{

    private float mLastX;
    private float mLastY;
    private int touchSlop;
    private Scroller scroller;

    private String TAG = "DragView2";

    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        touchSlop = configuration.getScaledTouchSlop();
        scroller = new Scroller(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        boolean intercepted = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                    intercepted = true;
                } else {

                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_MOVE: {
                float diffY = y - mLastY;
                float diffX = x - mLastX;
                if (diffY > touchSlop && diffY > Math.abs(diffX)) {
                    ListView lv = (ListView) findViewById(R.id.lv);
                    //ListView childAt = (ListView) getChildAt(0);
                    boolean b = lv.getFirstVisiblePosition() == 0;
                    boolean b1 = lv.getChildAt(0).getTop() >= 0;
                    if (b && b1) {
                        intercepted = true;
                    }
                }
            }
            break;
            case MotionEvent.ACTION_UP:
                intercepted = true;
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
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE: {
                float diffY = y - mLastY;
                if (getScrollY() <= 0) {
                    scrollBy(0, -(int) diffY);
                    setAlpha(1.0f - Math.abs(getScrollY()) / (float) getHeight());
                }
            }
            break;
            case MotionEvent.ACTION_UP: {

                float rate = Math.abs(getScrollY()) / (float) getHeight();

                if (rate > 0.6f) {//隐藏
                    scroller.startScroll(0, getScrollY(), 0, -getHeight() + Math.abs(getScrollY()));
                } else if (rate > 0.1f) {//显示
                    scroller.startScroll(0, getScrollY(), 0, -getScrollY(), 1000);
                } else {
                    scrollTo(0, 0);
                }
                invalidate();
            }
            break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    @Override
    public void computeScroll() {
        boolean b = scroller.computeScrollOffset();
        Log.i(TAG, "computeScroll=" + b);
        if (b) {
            float rate = Math.abs(scroller.getCurrY()) / (float) getHeight();
            Log.i(TAG, "computeScroll=" + scroller.getCurrY());
            this.setAlpha(1.0f - rate);

            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }
}
