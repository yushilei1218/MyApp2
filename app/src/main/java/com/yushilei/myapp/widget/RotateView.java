package com.yushilei.myapp.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

/**
 * @auther by yushilei.
 * @time 2017/2/22-11:10
 * @desc
 */

public class RotateView extends ViewGroup {
    private static final String TAG = "RotateView";

    /**
     * 当前已旋转的角度 ，当改变该角度时，并重新布局则达到旋转的效果
     */
    private float mCurAngle = 0f;
    /**
     * 记录每次旋转开始时的角度
     */
    private float mStartRotateAngle;
    /**
     * 当前ViewGroup 旋转的中心点坐标
     */
    private PointF mCenterPoint;
    /**
     * 围绕中心点旋转的半径
     */
    private double mR;
    /**
     * 缓存每个Child 布局时所在的位置
     */
    private PointF mChildPoint = new PointF();
    /**
     * 系统可检测的最小滑动距离
     */
    private int touchSlop;
    /**
     * 记录每次MotionEvent 的坐标值
     */
    private float mLastX;
    private float mLastY;
    /**
     * 记录开始滑动的时间
     */
    private long mStartRotateTime;
    /**
     * 触发弹性旋转的边界值
     */
    private static final float ROTATE_RATE = 500;
    /**
     * 弹性旋转Runnable
     */
    private RotateRunnable action;
    /**
     * 当前是否处于弹性旋转状态
     */
    private boolean isFling;

    public RotateView(Context context) {
        this(context, null);
    }

    public RotateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCenterPoint = new PointF();
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //1、对每个Child进行测量 在测量之后才能获取到Child的 MeasureHeight 和MeasureWidth
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int height = getHeight();
        //2、初始化旋转的中心点
        mCenterPoint.x = width / 2;
        mCenterPoint.y = height / 2;
        //3、初始化旋转的半径
        mR = ((Math.min(width, height) / 2d) * 0.6d);
        Log.i(TAG, "Angle=" + mCurAngle + " Cx=" + mCenterPoint.x + " Cy=" + mCenterPoint.y);
        if (getChildCount() > 0) {
            //4、每相邻的Child的角度间距
            float mPerAngle = 360f / getChildCount();
            //5、根据角度开启每个Child 排版过程
            for (int i = 0; i < getChildCount(); i++) {
                //6、计算当前Child角度 保证其在Child在 360>angle>=0
                float angle = (mCurAngle + mPerAngle * i) % 360f;
                if (angle < 0) { //比如Child当前角度为-30，那么它其实就是 360-30=330度是一样的
                    angle = 360f - Math.abs(angle) % 360f;
                }
                View child = getChildAt(i);
                //7、计算当前Child位于哪个象限
                int quadrant = getQuadrant(angle);
                //8、计算该角度正切值 可能为0
                double tanA = getTanA(quadrant, angle);
                //9、根据正切值获取A边边长
                double edgeA = getEdgeA(tanA);//A边
                double edgeB = edgeA == 0 ? mR : edgeA / tanA;//B边
                Log.i(TAG, "child=" + i + " 象限=" + quadrant);
                //10、根据child所在象限 及 A边 B边 计算Child中心点位置坐标
                computeChildLocation(quadrant, edgeA, edgeB);
                //11、根据Child中心点坐标及Child大小 进行布局
                layoutChild(child);
            }
        }
    }

    /**
     * 根据Child中心点位置进行布局
     */
    private void layoutChild(View child) {
        int measuredHeight = child.getMeasuredHeight();
        int measuredWidth = child.getMeasuredWidth();
        int left = (int) (mChildPoint.x - measuredWidth / 2);
        int top = (int) (mChildPoint.y - measuredHeight / 2);
        int right = (int) (mChildPoint.x + measuredWidth / 2);
        int bottom = (int) (mChildPoint.y + measuredHeight / 2);
        child.layout(left, top, right, bottom);
    }

    /**
     * 根据child所在象限  及A边 B边 计算其所在位置
     */
    private void computeChildLocation(int quadrant, double edgeA, double edgeB) {
        //1象限  tan A= y/x;  2象限 tan A =x/y ；3象限 y/x  ;4象限 x/y
        switch (quadrant) {
            case 1://第一象限
                mChildPoint.x = (float) (mCenterPoint.x + edgeB);
                mChildPoint.y = (float) (mCenterPoint.y - edgeA);
                break;
            case 2://第二象限
                mChildPoint.x = (float) (mCenterPoint.x - edgeA);
                mChildPoint.y = (float) (mCenterPoint.y - edgeB);
                break;
            case 3://第三象限
                mChildPoint.x = (float) (mCenterPoint.x - edgeB);
                mChildPoint.y = (float) (mCenterPoint.y + edgeA);
                break;
            default://第四象限
                mChildPoint.x = (float) (mCenterPoint.x + edgeA);
                mChildPoint.y = (float) (mCenterPoint.y + edgeB);
                break;
        }
    }

    /**
     * 根据正切值获取A边长度
     */
    private double getEdgeA(double tanA) {
        if (tanA == 0) {//如果正切值=0 ，那边A边长度=0，B边长=半径
            return 0;
        } else {//否则根据直角三角函数 a²+b²=c² tan(A)= a/b
            return Math.sqrt((mR * mR * tanA * tanA) / (1 + tanA * tanA));
        }
    }

    /**
     * 角度转弧度并获取正切值
     *
     * @param quadrant 象限
     * @param angle    角度
     * @return 正切值
     */
    public double getTanA(int quadrant, float angle) {
        float A;
        switch (quadrant) {
            case 1:
                A = angle;
                break;
            case 2:
                A = angle - 90;
                break;
            case 3:
                A = angle - 180;
                break;
            default:
                A = angle - 270;
                break;
        }
        //角度转弧度  求正切值
        return Math.tan(Math.toRadians(A));
    }

    /**
     * 获取象限
     */
    public int getQuadrant(float rAngle) {
        if (rAngle >= 0 && rAngle < 90) {
            return 1;
        }
        if (rAngle >= 90 && rAngle < 180) {
            return 2;
        }
        if (rAngle >= 180 && rAngle < 270) {
            return 3;
        }
        return 4;
    }

    public void setMCurAngle(float mCurAngle) {
        this.mCurAngle = mCurAngle;
        requestLayout();
    }

    public void startAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "MCurAngle", mCurAngle, mCurAngle + 720f);
        animator.setDuration(5 * 1000);
        animator.start();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();

        boolean intercepted = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //Down首先判断当前是否处于弹性旋转状态
                if (isFling) {//如果是则重置状态，并移除弹性旋转，也就是按下立刻停止旋转
                    isFling = false;
                    removeCallbacks(action);
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE: {
                //判断滑动的距离 只有大于系统的可识别滑动距离则容器拦截事件
                float diffX = Math.abs(x - mLastX);
                float diffY = Math.abs(y - mLastY);
                if (diffX >= touchSlop || diffY >= touchSlop) {
                    //记录开始旋转的角度
                    mStartRotateAngle = mCurAngle;
                    //记录开始旋转的时间
                    mStartRotateTime = System.currentTimeMillis();
                    intercepted = true;
                }
            }
            break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mLastX = x;
        mLastY = y;

        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                //计算上一次事件所在的角度
                float startAngle = getAngle(mLastX, mLastY);
                //计算本次事件所在的角度
                float endAngle = getAngle(x, y);
                float changeAngle = startAngle - endAngle;
                //获取当前事件所在的象限
                int quadrant = getQuadrant(x, y);
                float curAngle;
                if (quadrant == 1 || quadrant == 4) {
                    curAngle = mCurAngle + changeAngle;
                } else {
                    curAngle = mCurAngle - changeAngle;
                }
                //设置当前旋转角度并重新布局
                setMCurAngle(curAngle);
            }
            break;
            case MotionEvent.ACTION_UP: {
                long rotateDuration = System.currentTimeMillis() - mStartRotateTime;
                float sweepAngle = mCurAngle - mStartRotateAngle;
                //计算每秒活动的角度
                float speed = sweepAngle * 1000 / rotateDuration;
                Log.i(TAG, "speed=" + speed);
                if (Math.abs(speed) > ROTATE_RATE) {
                    action = new RotateRunnable(speed);
                    post(action);
                }
            }
            break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    private float getAngle(float xTouch, float yTouch) {
        double x = xTouch - mCenterPoint.x;
        double y = yTouch - mCenterPoint.y;
        return (float) (Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI);
    }

    public int getQuadrant(float x, float y) {
        if (x >= mCenterPoint.x) {
            return y >= mCenterPoint.y ? 1 : 4;
        } else {
            return y < mCenterPoint.y ? 2 : 3;
        }
    }


    public class RotateRunnable implements Runnable {
        RotateRunnable(float speed) {
            this.speed = speed;
        }

        float speed;

        @Override
        public void run() {
            if (Math.abs(speed) < 20) {
                isFling = false;
                return;
            }
            float addAngle = mCurAngle + (speed / 30);
            setMCurAngle(addAngle);
            isFling = true;
            speed /= 1.0666F;
            postDelayed(this, 30);
        }
    }
}
