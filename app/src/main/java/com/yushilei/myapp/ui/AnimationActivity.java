package com.yushilei.myapp.ui;

import android.Manifest;
import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;

import butterknife.BindView;

public class AnimationActivity extends BaseActivity {
    @BindView(R.id.animation_et)
    EditText et;
    @BindView(R.id.animation_layout)
    LinearLayout layout;
    @BindView(R.id.animation_layout_transition)
    LinearLayout layoutTransition;
    @BindView(R.id.animation_img)
    ImageView img;

    @Override
    public int getLayoutId() {
        return R.layout.activity_animation;
    }

    @Override
    protected void onInitViews() {
        LayoutTransition transitioner = new LayoutTransition();
        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "rotationY", 0f, 360f, 0f);
        transitioner.setAnimator(LayoutTransition.APPEARING, animIn);
        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "rotation", 0f, 90f, 0f);
        transitioner.setAnimator(LayoutTransition.DISAPPEARING, animOut);

        layoutTransition.setLayoutTransition(transitioner);


    }

    boolean expand = true;
    int index = 0;

    public void animator(View view) {

        if (expand) {
            expand = false;
            ObjectAnimator animator = ObjectAnimator.ofInt(new Wrapper(et), "Width", et.getWidth(), et.getWidth() + 200);
            animator.setDuration(1000);
            animator.start();
        } else {
            expand = true;
            ObjectAnimator animator = ObjectAnimator.ofInt(new Wrapper(et), "Width", et.getWidth(), et.getWidth() - 200);
            animator.setDuration(1000);
            animator.start();
        }

        if (index <= 0) {
            index++;
            Button child = new Button(this);
            child.setText("animateLayoutChanges");
            layout.addView(child);
            Button child2 = new Button(this);
            child2.setText("LayoutTransition");
            layoutTransition.addView(child2);
        } else {
            if (index >= 1) {
                index--;
                layout.removeViewAt(index);
                layoutTransition.removeViewAt(index);
            }
        }
    }

    public void propertyValueHolder(View view) {
        view.setPivotY(view.getHeight() / 2);
        view.setPivotX(view.getWidth() / 2);
        PropertyValuesHolder rotate = PropertyValuesHolder.ofFloat("rotation", 0, 15, 0, -15, 0);
        PropertyValuesHolder scale = PropertyValuesHolder.ofFloat("ScaleX", 0.8f, 1, 0.8f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, rotate, scale);
        animator.setDuration(300);
        animator.setRepeatCount(10);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();
    }

    public void keyFrame(View view) {
        Keyframe frame0 = Keyframe.ofFloat(0f, 0);
        Keyframe frame1 = Keyframe.ofFloat(0.1f, -20f);
        Keyframe frame2 = Keyframe.ofFloat(0.2f, 20f);
        Keyframe frame3 = Keyframe.ofFloat(0.3f, -20f);
        Keyframe frame4 = Keyframe.ofFloat(0.4f, 20f);
        Keyframe frame5 = Keyframe.ofFloat(0.5f, -20f);
        Keyframe frame6 = Keyframe.ofFloat(0.6f, 20f);
        Keyframe frame7 = Keyframe.ofFloat(0.7f, -20f);
        Keyframe frame8 = Keyframe.ofFloat(0.8f, 20f);
        Keyframe frame9 = Keyframe.ofFloat(0.9f, -20f);
        Keyframe frame10 = Keyframe.ofFloat(1, 0);
        PropertyValuesHolder holder = PropertyValuesHolder
                .ofKeyframe("rotation", frame0, frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10);

        Animator animator = ObjectAnimator.ofPropertyValuesHolder(img, holder);
        animator.setDuration(1000);
        animator.start();
    }

    private class Wrapper {
        View target;

        public Wrapper(View target) {
            this.target = target;
        }

        public void setWidth(int width) {
            target.getLayoutParams().width = width;

            target.requestLayout();
        }
    }
}
