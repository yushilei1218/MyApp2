package com.yushilei.myapp.ui;

import android.graphics.PixelFormat;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;

import butterknife.OnClick;

public class WindowActivity extends BaseActivity implements View.OnTouchListener, View.OnClickListener {


    private WindowManager.LayoutParams lp;
    private View floatView;
    private WindowManager wm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_window;
    }

    @Override
    protected void onInitViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @OnClick({
            R.id.set_over_lay_btn
    })
    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.set_over_lay_btn:
                setWindowOverLay();//设置悬浮BTN
                break;
        }
    }

    private void setWindowOverLay() {
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        floatView = LayoutInflater.from(this).inflate(R.layout.floating_btn, null);

        lp = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                , 0, 0, PixelFormat.TRANSPARENT);
        lp.flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        lp.gravity = Gravity.LEFT | Gravity.TOP;

        lp.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        lp.x = 100;
        lp.y = 300;
        floatView.setLayoutParams(lp);
        wm.addView(floatView, lp);

        floatView.setOnTouchListener(this);
        floatView.setOnClickListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getRawX();
        float y = event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int width = floatView.getWidth();
                int height = floatView.getHeight();
                lp.x = (int) x - width / 2;
                lp.y = (int) y - height / 2;
                wm.updateViewLayout(floatView, lp);
                break;
        }

        return false;
    }

    @Override
    public void onClick(View v) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        animation.setDuration(500);
        animation.setRepeatCount(1);
        floatView.startAnimation(animation);
    }

    @Override
    protected void onDestroy() {
        wm.removeViewImmediate(floatView);
        super.onDestroy();
    }
}
