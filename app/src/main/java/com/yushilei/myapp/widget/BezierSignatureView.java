package com.yushilei.myapp.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @auther by yushilei.
 * @time 2017/3/30-11:26
 * @desc
 */

public class BezierSignatureView extends View {

    private Path mPath = new Path();

    PointF cacheMidPointF = new PointF();
    PointF lastPointF = new PointF();
    PointF newPointF = new PointF();

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Canvas mCanvas;
    private Bitmap bitmap;

    public BezierSignatureView(Context context) {
        super(context);
    }

    public BezierSignatureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(4);
        paint.setStrokeJoin(Paint.Join.ROUND);

        bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.RGB_565);
        mCanvas = new Canvas(bitmap);
        mCanvas.drawColor(Color.WHITE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(mPath, paint);
        mCanvas.drawPath(mPath, paint);

    }

    boolean isFirstMoveP = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isFirstMoveP = true;
                break;
            case MotionEvent.ACTION_MOVE:
                newPointF.x = x;
                newPointF.y = y;
                comMidpointF(lastPointF, newPointF);
                if (isFirstMoveP) {//第一个Move点
                    isFirstMoveP = false;
                    mPath.moveTo(cacheMidPointF.x, cacheMidPointF.y);
                } else {
                    mPath.quadTo(lastPointF.x, lastPointF.y, cacheMidPointF.x, cacheMidPointF.y);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!isFirstMoveP) {//如果只是点击一下 就弹起
                    mPath.lineTo(x, y);
                }
                break;
        }
        lastPointF.x = x;
        lastPointF.y = y;
        invalidate();
        return true;
    }

    private void comMidpointF(PointF a, PointF b) {
        cacheMidPointF.x = (a.x + b.x) / 2f;
        cacheMidPointF.y = (a.y + b.y) / 2f;
    }

    public void clear() {
        mPath.reset();
        mCanvas.drawColor(Color.WHITE);
        invalidate();
    }

    public Bitmap getSignBitmap() {
        return bitmap;
    }

    public Bitmap getDrawBitmap() {
        destroyDrawingCache();
        buildDrawingCache();
        Bitmap drawingCache = getDrawingCache();
        return drawingCache;
    }

}
