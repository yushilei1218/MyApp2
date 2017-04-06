package com.yushilei.myapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @auther by yushilei.
 * @time 2017/4/6-12:49
 * @desc
 */

public class XferModeView extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int r;
    TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);

    PorterDuffXfermode xfermode;
    String mode;

    public void setXfermode(PorterDuffXfermode xfermode, String modeName) {
        this.xfermode = xfermode;
        this.mode = modeName;
        invalidate();
    }

    public XferModeView(Context context) {
        this(context, null);
    }

    public XferModeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint.setColor(Color.YELLOW);
        mPaint.setDither(true);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(40);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        r = w / 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        int count = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);
        mPaint.setColor(Color.YELLOW);

        canvas.drawCircle(r + 5, r + 5, r, mPaint);
        canvas.save();
        canvas.translate(r + 5, r + 5);
        mPaint.setColor(Color.parseColor("#37fbf1"));
        if (xfermode != null)
            mPaint.setXfermode(xfermode);
        canvas.drawRect(0, 0, getWidth() - r - 10, getHeight() - r - 10, mPaint);
        canvas.restore();
        if (mode != null) {
            canvas.translate(getWidth() / 2, getHeight() / 2);
            canvas.drawText(mode, 0, 0, textPaint);
        }
        mPaint.setXfermode(null);
        canvas.restoreToCount(count);
    }
}
