package com.yushilei.myapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;
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
    private Path firstPath = new Path();
    private Path secPath = new Path();

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);

    public PageTurningView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(Color.RED);
        paint2.setStrokeWidth(2f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        paint2.setColor(Color.LTGRAY);
        for (int i = 0; i < 20; i++) {
            canvas.drawLine(0, getHeight() * i / 20f, getWidth(), getHeight() * i / 20f, paint2);
            canvas.drawLine(getWidth() * i / 15, 0, getWidth() * i / 15, getHeight(), paint2);

        }

        firstPath.moveTo(0, 0);
        firstPath.lineTo(getWidth(), getHeight() / 4);
        firstPath.lineTo(0, getHeight() / 2);
        firstPath.close();
        secPath.moveTo(100, 100);
        secPath.lineTo(getWidth(), getHeight() / 4);
        secPath.lineTo(0, getHeight() / 2);
        secPath.close();
        paint2.setColor(Color.RED);
        canvas.drawPath(firstPath, paint2);
        paint2.setColor(Color.RED);
        canvas.drawPath(secPath, paint2);

        canvas.save();
        canvas.clipPath(firstPath);
        canvas.clipPath(secPath, Region.Op.REVERSE_DIFFERENCE);
        canvas.drawColor(Color.BLACK);

        canvas.restore();

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
