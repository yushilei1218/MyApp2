package com.yushilei.myapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * @auther by yushilei.
 * @time 2017/3/30-16:08
 * @desc
 */

public class PageTurningView extends TextView {
    public PageTurningView(Context context) {
        super(context);
    }

    private Rect touchRect = new Rect();

    private boolean isDrag = false;

    private PointF dragPointF = new PointF();

    private Path mPath = new Path();

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public PageTurningView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isDrag) {
            canvas.drawPath(mPath, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        dragPointF.x = x;
        dragPointF.y = y;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (checkShowBezier(x, y)) {
                    isDrag = true;
                }
                break;
            case MotionEvent.ACTION_MOVE: {
                if (isDrag)
                    computePath();
            }
            break;
            case MotionEvent.ACTION_UP:
                break;
        }
        if (isDrag) {
            invalidate();
        }
        return true;
    }

    private void computePath() {
        mPath.reset();
        mPath.moveTo(0, getHeight());
        mPath.lineTo(getWidth(), getHeight());
        mPath.lineTo(getWidth(), 0);
        mPath.quadTo(getWidth(), dragPointF.y, dragPointF.x, dragPointF.y);
        mPath.quadTo(dragPointF.x, getHeight(), 0, getHeight());
        mPath.close();
    }

    private boolean checkShowBezier(float x, float y) {
        if (x > 0.8f * getWidth() && y > 0.8f * getHeight()) {
            return true;
        }
        return false;
    }
}
