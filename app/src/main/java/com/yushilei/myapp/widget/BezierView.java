package com.yushilei.myapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @auther by yushilei.
 * @time 2017/2/23-14:55
 * @desc
 */

public class BezierView extends View {
    private static final String TAG = "BezierView";

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //x正数  y 负数
    private Point targetPoint = new Point();
    private Point touchPoint = new Point();
    private Point controlPoint = new Point();
    private int mR;

    private boolean isDrag = false;
    private Rect rect;

    Path mPath = new Path();

    public BezierView(Context context) {
        super(context);
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        targetPoint.x = getWidth() / 2;
        targetPoint.y = -getHeight() / 2;

        mR = Math.min(getWidth(), getHeight()) / 10;
        rect = new Rect(targetPoint.x - mR, targetPoint.y - mR, targetPoint.x + mR, targetPoint.y + mR);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(targetPoint.x, targetPoint.y, mR, paint);
        if (isDrag) {
            canvas.drawCircle(touchPoint.x, -touchPoint.y, mR, paint);

            controlPoint.x = (targetPoint.x + touchPoint.x) / 2;
            controlPoint.y = (targetPoint.y + touchPoint.y) / 2;
            double edgeC = Math.sqrt(Math.pow((controlPoint.x - targetPoint.x), 2)
                    + Math.pow((controlPoint.y - targetPoint.y), 2));
            double edgeB = Math.sqrt(Math.pow(edgeC, 2) - mR * mR);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (rect.contains((int) x, (int) y)) {
                    isDrag = true;
                    touchPoint.x = (int) x;
                    touchPoint.y = -(int) y;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDrag) {
                    touchPoint.x = (int) x;
                    touchPoint.y = -(int) y;
                }
                break;
            case MotionEvent.ACTION_UP:
                isDrag = false;
                mPath.reset();
                break;
        }
        if (isDrag) {
            invalidate();
        }
        return true;
    }
}
