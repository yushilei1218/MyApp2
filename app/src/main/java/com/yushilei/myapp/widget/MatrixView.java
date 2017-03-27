package com.yushilei.myapp.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

/**
 * @auther by yushilei.
 * @time 2017/3/27-15:41
 * @desc
 */

public class MatrixView extends View implements ScaleGestureDetector.OnScaleGestureListener {
    private static final String TAG = "MatrixView";
    Context context;
    Matrix matrix = new Matrix();
    private Bitmap bitmap;
    private float mLastY;
    private float mLastX;

    private float curTranslatex;
    private float curTranslateY;

    ScaleGestureDetector detector;
    private boolean isScale;

    public MatrixView(Context context) {
        super(context);
    }

    public MatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        detector = new ScaleGestureDetector(context, this);
    }

    public void setBitmap(@DrawableRes int rid, Matrix matrix) {
        if (matrix != null) {
            this.matrix = matrix;
        }
        bitmap = BitmapFactory.decodeResource(context.getResources(), rid);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, matrix, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
      detector.onTouchEvent(event);
        if (!isScale) {
            float x = event.getX();
            float y = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE: {
                    float diffx = x - mLastX;
                    float diffy = y - mLastY;
                    curTranslatex = curTranslatex + diffx;
                    curTranslateY = curTranslateY + diffy;
                    matrix.setTranslate(curTranslatex, curTranslateY);
                }
                break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            mLastX = x;
            mLastY = y;
            invalidate();
        }

        return true;
    }

    //----ScaleGestureDetector----
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scaleFactor = detector.getScaleFactor();
        float focusX = detector.getFocusX();
        float focusY = detector.getFocusY();
        Log.i(TAG, "onScale scaleFactor=" + scaleFactor);
        matrix.setScale(scaleFactor, scaleFactor, focusX, focusY);
        invalidate();
        return false;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        isScale = true;
        Log.i(TAG, "onScaleBegin");
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        isScale = false;
        Log.i(TAG, "onScaleEnd");
    }
}
