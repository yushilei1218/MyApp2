package com.yushilei.myapp.ui;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;

import butterknife.BindView;

public class AnimationActivity extends BaseActivity {
    @BindView(R.id.animation_et)
    EditText et;

    @Override
    public int getLayoutId() {
        return R.layout.activity_animation;
    }

    @Override
    protected void onInitViews() {

    }

    boolean expand = true;

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
