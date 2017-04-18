package com.yushilei.myapp.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @auther by yushilei.
 * @time 2017/4/18-16:04
 * @desc
 */

public class ColorTransferView extends View {
    TextPaint mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private static final String TAG = "ColorTransferView";

    public ColorTransferView(Context context) {
        super(context);
    }

    Rect rect = new Rect();
    float percent = 0f;//-1 0 1

    public ColorTransferView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(40);
    }


    private String mText = "测试文案";

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "width=" + getWidth() + " height=" + getHeight());
        rect.set(0, 0, getWidth(), getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        drawText(canvas);
        int width = getWidth();
        rect.left = (int) ((1 - percent) * width - width);
        rect.right = rect.left + width/2;
        canvas.clipRect(rect);
        mPaint.setColor(Color.RED);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.drawText(mText, 0, mText.length(), mPaint);
        canvas.restore();
    }

    public void setPercent(float percent) {
        this.percent = percent;
        postInvalidate();
    }

    public void startAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "Percent", -1, 1);
        animator.setDuration(5000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(10);
        animator.start();
    }
}
