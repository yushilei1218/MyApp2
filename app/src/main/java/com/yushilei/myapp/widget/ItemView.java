package com.yushilei.myapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * @auther by yushilei.
 * @time 2017/2/21-15:30
 * @desc
 */

public class ItemView extends TextView {
    private final String TAG = "ItemView";

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode != MeasureSpec.EXACTLY) {
            throw new RuntimeException("ItemView 必须有宽度");
        }
        int hSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, hSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
