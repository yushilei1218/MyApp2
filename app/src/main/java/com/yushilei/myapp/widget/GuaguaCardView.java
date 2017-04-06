package com.yushilei.myapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.yushilei.myapp.R;

/**
 * @auther by yushilei.
 * @time 2017/4/6-10:45
 * @desc
 */

public class GuaguaCardView extends View {
    private static final String TAG = "GuaguaCardView";

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mOuterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Bitmap bitmap;

    private float whRatio;

    private Path mPath = new Path();
    private PorterDuffXfermode xfermode;

    public GuaguaCardView(Context context) {
        super(context);
    }

    public GuaguaCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GuaguaCardView);
        whRatio = a.getFloat(R.styleable.GuaguaCardView_w_h_ratio, 1f);
        a.recycle();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(20f);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.guaguaka);
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "whRatio=" + whRatio);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode != MeasureSpec.EXACTLY) {
            throw new RuntimeException("宽度必须限定");
        }
        int height = (int) (size / whRatio);

        super.onMeasure(widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPath.isEmpty()) {
            canvas.drawColor(Color.LTGRAY);
        } else {
            canvas.drawBitmap(bitmap, 0, 0, null);
            // mPaint.setXfermode(null);
            mPaint.setColor(Color.TRANSPARENT);
            mPaint.setXfermode(xfermode);
            canvas.drawPath(mPath, mPaint);
            mOuterPaint.setColor(Color.parseColor("#00ffdddd"));
            //mPaint.setXfermode(null);
            canvas.drawRect(0, 0, getWidth(), getHeight(), mOuterPaint);
        }
    }

    PointF mLastP = new PointF();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mLastP.x = x;
        mLastP.y = y;
        invalidate();
        return true;
    }
}
