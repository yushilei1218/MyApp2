package com.yushilei.myapp.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import com.yushilei.myapp.R;

/**
 * @auther by yushilei.
 * @time 2017/2/28-10:00
 * @desc
 */

public class RefreshView extends FrameLayout {

    private static final String TAG = "RefreshView";

    private static final float RotateRate = 0.6f;//旋转系数

    private static final float SlideRate = 0.7f;//滑动系数

    private View img;
    private View header;
    private View child;
    private ObjectAnimator ani;

    public RefreshView(Context context) {
        super(context);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        MarginLayoutParams lp = (MarginLayoutParams) header.getLayoutParams();
        lp.topMargin = -header.getMeasuredHeight();
        header.setLayoutParams(lp);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 2)
            throw new RuntimeException("RefreshView最多容纳2个子View");
        Log.d(TAG, "onFinishInflate");
        header = findViewById(R.id.refresh_header);
        img = header.findViewById(R.id.refresh_img);
        child = getChildAt(0);
    }

    float mLastX;
    float mLastY;

    boolean isDragged;
    boolean isReleased;
    boolean isOverEdge;

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
                if (refreshListener != null && diffY > 0 && diffY > Math.abs(diffX)) {
                    intercepted = refreshListener.canRefresh();
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        if (intercepted) {
            isDragged = true;
        }
        mLastX = x;
        mLastY = y;
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        isOverEdge = child.getY() >= header.getHeight();

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                float diffY = y - mLastY;
                if (child.getY() + diffY >= 0) {
                    float newY = child.getY() + diffY * SlideRate;
                    if (isOverEdge) {
                        header.setY(0);
                    } else {
                        header.setY(newY - header.getHeight());
                    }
                    child.setY(newY);

                    img.setRotation(img.getRotation() + diffY / RotateRate);
                } else {
                    child.setY(0);
                }
            }
            break;
            case MotionEvent.ACTION_UP: {
                isDragged = false;
                isReleased = true;

                if (isOverEdge) {//加载
                    ani = ObjectAnimator.ofFloat(child, "Y", child.getY(), 0f);
                    ani.setDuration((long) (child.getY() * 5));
                    ani.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {

                            float y1 = child.getY() - header.getHeight();

                            if (isOverEdge) {
                                img.setRotation(img.getRotation() - 10);
                            } else {
                                header.setY(y1);
                            }
                        }
                    });
                    ani.start();

                } else {
                    if (child.getY() <= 10) {
                        child.setY(0);
                        header.setY(-header.getHeight());
                    } else {//弹性
                        ani = ObjectAnimator.ofFloat(child, "Y", child.getY(), 0f);
                        ani.setDuration((long) (child.getY() * 5));
                        ani.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                int bottom = header.getBottom();
                                float y1 = child.getY() - header.getHeight();
                                img.setRotation(img.getRotation() + (y1 - bottom) / RotateRate);
                                if (isOverEdge) {

                                } else {
                                    header.setY(y1);
                                }
                            }
                        });
                        ani.start();
                    }
                }
            }
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
