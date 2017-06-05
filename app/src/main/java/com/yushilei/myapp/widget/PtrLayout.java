package com.yushilei.myapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.yushilei.myapp.R;

/**
 * @auther by yushilei.
 * @time 2017/6/5-13:57
 * @desc
 */

public class PtrLayout extends ViewGroup {
    private static final String TAG = "PtrLayout";
    private View mHeaderView;

    private View mContentView;

    PtrRrHelper ptrRrHelper = new PtrRrHelper() {
        @Override
        public boolean isCanRefresh() {
            return false;
        }
    };
    private int mHeaderId;

    public PtrLayout(Context context) {
        this(context, null);
    }

    public PtrLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PtrLayout);
        mHeaderId = array.getResourceId(R.styleable.PtrLayout_layout_header, 0);
        array.recycle();
        if (mHeaderId != 0) {
            mHeaderView = LayoutInflater.from(context).inflate(mHeaderId, this, false);
            addView(mHeaderView);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (mHeaderView != null) {
            mHeaderView.layout(0, -mHeaderView.getMeasuredHeight(), mHeaderView.getMeasuredWidth(), 0);
        }
        if (mContentView != null) {
            mContentView.layout(0, 0, mContentView.getMeasuredWidth(), mContentView.getMeasuredHeight());
        }
    }

    @Override
    public void addView(View child, int index, LayoutParams params) {
        if (child.getId() != mHeaderView.getId()) {
            if (mContentView == null) {
                mContentView = child;
            } else {
                if (mContentView.getId() != child.getId())
                    throw new RuntimeException("只有一个Child");
            }
        }
        super.addView(child, index, params);
    }

    float mLastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ptrRrHelper.isCanRefresh()) {
            return dispatchTouchEventCurrent(ev);
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }

    private boolean isDragged = false;

    private boolean dispatchTouchEventCurrent(MotionEvent ev) {
        Log.i(TAG, "dispatchTouchEventCurrent");
        float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDragged = super.dispatchTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                if (y - mLastY > 0) {
                    isDragged = true;
                }
                if (isDragged) {
                    //
                    scrollBy(0, (int) (mLastY - y));
                }
                break;
            case MotionEvent.ACTION_UP:
                isDragged = false;
                break;
        }
        mLastY = y;
        return isDragged;
    }

    public void setPtrRrHelper(PtrRrHelper ptrRrHelper) {
        this.ptrRrHelper = ptrRrHelper;
    }

    public interface PtrRrHelper {
        boolean isCanRefresh();
    }

    public interface PtrUIHelper {
        void onUIReset(PtrLayout ptr, View header);

        void onUIRefreshBegin();
    }

}
