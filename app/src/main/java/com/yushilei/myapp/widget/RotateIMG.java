package com.yushilei.myapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * @auther by yushilei.
 * @time 2017/3/3-11:07
 * @desc
 */

public class RotateIMG extends ImageView {

    private Run action;

    public RotateIMG(Context context) {
        super(context);
    }

    public RotateIMG(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        action = new Run(this);
        postDelayed(action, 30);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(action);
    }

    public class Run implements Runnable {
        View view;

        public Run(View view) {
            this.view = view;
        }

        @Override
        public void run() {
            view.setRotation((view.getRotation() + 10) % 360);
            view.postDelayed(action, 30);
        }
    }
}
