package com.yushilei.myapp.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.yushilei.myapp.R;
import com.yushilei.myapp.util.Circle;
import com.yushilei.myapp.util.CircleUtil;

/**
 * @auther by yushilei.
 * @time 2017/2/23-14:55
 * @desc
 */

public class BezierView extends View {
    private static final String TAG = "BezierView";

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**
     * 目标点坐标
     */
    private PointF targetPoint = new PointF();
    /**
     * 触摸点坐标
     */
    private PointF touchPoint = new PointF();
    /**
     * 目标点坐标 与 触摸点坐标 连线的中间点坐标 也就是control点
     */
    private PointF controlPoint = new PointF();

    /**
     * Control点与2圆的4个交点
     */
    private Point focusPointA = new Point();
    private Point focusPointB = new Point();
    private Point focusPointC = new Point();
    private Point focusPointD = new Point();
    /**
     * 目标圆半径，也是touch圆半径
     */
    private int mR;
    /**
     * 是否处于被拖拽状态
     */
    private boolean isDrag = false;
    /**
     * 是否分割目标和touch圆
     */
    private boolean isSplit = false;
    /**
     * 状态：是否清除内容
     */
    private boolean isDiscard = false;
    /**
     * 可触发Drag的touch区域
     */
    private RectF mDragRange;
    /**
     * 作为4个交点 及control点 的闭合path
     */
    Path mPath = new Path();
    /**
     * control点 和目标中心点 距离；也是是否需要DrawPath的边界值
     * 当该距离大于mR 意味着目标圆 和touch的圆无交点需要绘制中间区域
     */
    private double needDrawClosePathDistance;
    /**
     * 可绘制的文本
     */
    private String text = "99+";

    TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    /**
     * 文本偏移量 （使得文本居中时需要）
     */
    private int mTextOffset;

    private static final float mSplitRate = 2;

    private double mSplitDistance;

    public BezierView(Context context) {
        super(context);
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        float dimension = context.getResources().getDimension(R.dimen.textSize);
        textPaint.setTextSize(dimension);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.WHITE);

        Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
        mTextOffset = (Math.abs(fontMetricsInt.descent) - Math.abs(fontMetricsInt.ascent)) / 2;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        targetPoint.x = getWidth() / 2;
        targetPoint.y = getHeight() / 2;

        mR = Math.min(getWidth(), getHeight()) / 10;

        mDragRange = new RectF(targetPoint.x - mR, targetPoint.y - mR, targetPoint.x + mR, targetPoint.y + mR);

        mSplitDistance = mSplitRate * mR;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isDiscard) {

        } else {
            if (!isSplit) {

                canvas.drawCircle(targetPoint.x, targetPoint.y, mR, paint);

            }
            if (isDrag) {
                canvas.drawCircle(touchPoint.x, touchPoint.y, mR, paint);
                if (!isSplit && needDrawClosePathDistance > mR) {
                    mPath.reset();
                    mPath.moveTo(focusPointA.x, focusPointA.y);
                    mPath.quadTo(controlPoint.x, controlPoint.y, focusPointC.x, focusPointC.y);
                    mPath.lineTo(focusPointD.x, focusPointD.y);
                    mPath.quadTo(controlPoint.x, controlPoint.y, focusPointB.x, focusPointB.y);
                    mPath.close();
                    canvas.drawPath(mPath, paint);
                }
                canvas.drawText(text, touchPoint.x, touchPoint.y - mTextOffset, textPaint);
            }
            if (!isSplit) {
                if (!TextUtils.isEmpty(text)) {
                    canvas.drawText(text, targetPoint.x, targetPoint.y - mTextOffset, textPaint);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        Log.i(TAG, "x=" + x + " ;y=" + y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mDragRange.contains((int) x, (int) y)) {
                    isDrag = true;
                    touchPoint.x = (int) x;
                    touchPoint.y = (int) y;
                    computeControl();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDrag) {
                    touchPoint.x = (int) x;
                    touchPoint.y = (int) y;
                    computeControl();
                }
                break;
            case MotionEvent.ACTION_UP: {
                isDiscard = needDrawClosePathDistance >= mSplitDistance;
                isDrag = false;
                isSplit = false;
                if (!isDiscard) {//开启一个弹跳的动画
                    //计算直线方程
                    float[] xyArr = computeTranslateAni(targetPoint, touchPoint);

                    if (xyArr != null) {
                        Log.i(TAG, xyArr[0] + " " + xyArr[1]);
                        TranslateAnimation animation = new TranslateAnimation(xyArr[0], -xyArr[0], xyArr[1], -xyArr[1]);
                        animation.setInterpolator(new AccelerateDecelerateInterpolator());
                        animation.setRepeatMode(Animation.REVERSE);
                        animation.setRepeatCount(4);
                        animation.setDuration(60);
                        BezierView.this.startAnimation(animation);
                    }
                }
                invalidate();
            }
            break;
        }
        if (isDrag) {
            invalidate();
        }
        return true;
    }

    private static final float TranslateAniLimited = 5;

    private float[] computeTranslateAni(PointF point1, PointF point2) {
        if (point1.x == point2.x && point1.y == point2.y) {
            return null;
        }
        float[] data = new float[2];
        if (point1.x == point2.x) {
            data[0] = 0;
            data[1] = TranslateAniLimited;
            return data;
        }
        if (point1.y == point2.y) {
            data[0] = TranslateAniLimited;
            data[1] = 0;
            return data;
        }

        float k = (point2.y - point1.y) / (point2.x - point1.x);
        float bEdge = (float) (TranslateAniLimited / (Math.sqrt(1 + k * k)));
        float aEdge = bEdge * k;
        data[0] = bEdge;
        data[1] = aEdge;

        Log.i("测试", "k=" + k + " x =" +   data[0] + " y=" + data[1]);
        return data;
    }

    public void computeControl() {
        controlPoint.x = (targetPoint.x + touchPoint.x) / 2;
        controlPoint.y = (targetPoint.y + touchPoint.y) / 2;
        needDrawClosePathDistance = Math.sqrt(Math.pow(targetPoint.x - controlPoint.x, 2) + Math.pow(targetPoint.y - controlPoint.y, 2));

        if (needDrawClosePathDistance >= mSplitDistance) {
            isSplit = true;
        }
        if (isSplit) {
            return;
        }
        double R2 = Math.sqrt(Math.pow(targetPoint.x - controlPoint.x, 2) + Math.pow(targetPoint.y - controlPoint.y, 2) - mR * mR);

        //3个圆
        double[] doubles = new CircleUtil(new Circle(targetPoint.x, targetPoint.y, mR)
                , new Circle(controlPoint.x, controlPoint.y, R2)).intersect();
        if (doubles != null && doubles.length == 4) {
            focusPointA.x = (int) doubles[0];
            focusPointA.y = (int) doubles[1];
            focusPointB.x = (int) doubles[2];
            focusPointB.y = (int) doubles[3];
        }

        double[] doubles2 = new CircleUtil(new Circle(touchPoint.x, touchPoint.y, mR)
                , new Circle(controlPoint.x, controlPoint.y, R2)).intersect();
        if (doubles2 != null && doubles2.length == 4) {
            focusPointC.x = (int) doubles2[0];
            focusPointC.y = (int) doubles2[1];
            focusPointD.x = (int) doubles2[2];
            focusPointD.y = (int) doubles2[3];
        }
    }
}
