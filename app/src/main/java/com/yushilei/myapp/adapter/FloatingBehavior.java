package com.yushilei.myapp.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

/**
 * @auther by yushilei.
 * @time 2017/3/13-15:47
 * @desc
 */

public class FloatingBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> implements Animator.AnimatorListener {

    private boolean show;

    public FloatingBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        float y = dependency.getY();
        int height = dependency.getHeight();
        int width = dependency.getWidth();
        child.setY(y + height - child.getMeasuredHeight() / 2);
        child.setX(width * 0.7f);

        if (Math.abs(y) > 0.6 * height) {//消失动画
            if (show) {
                show = false;
                ObjectAnimator animator = ObjectAnimator.ofFloat(child, "Alpha", 1f, 0f);
                animator.setDuration(200);
                animator.addListener(this);
                animator.start();
            }
        } else {//出现动画
            if (!show) {
                show = true;
                ObjectAnimator animator = ObjectAnimator.ofFloat(child, "Alpha", 0f, 1f);
                animator.setDuration(200);
                animator.addListener(this);
                animator.start();
            }

        }

        return true;
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (show) {
            ((View) ((ObjectAnimator) animation).getTarget()).setVisibility(View.VISIBLE);
        } else {
            ((View) ((ObjectAnimator) animation).getTarget()).setVisibility(View.GONE);
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
