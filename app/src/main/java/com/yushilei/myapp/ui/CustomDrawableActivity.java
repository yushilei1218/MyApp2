package com.yushilei.myapp.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.TextView;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.widget.RoundDrawable;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomDrawableActivity extends BaseActivity {
    @BindView(R.id.custom_drawable_img)
    TextView img;
    @BindView(R.id.custom_drawable_img2)
    TextView img2;
    @BindView(R.id.custom_drawable_tv)
    TextView tv;
    @BindView(R.id.transition_tv)
    TextView transition_tv;
    @BindView(R.id.clip_v)
    View clip_v;
    private ClipDrawable clipDrawable;
    private Handler handler;
    private Run r;

    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_drawable;
    }

    @Override
    protected void onInitViews() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a2);
        RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        bitmapDrawable.setCornerRadius(20);
        img2.setBackground(bitmapDrawable);

        tv.setBackground(bitmapDrawable);

        RoundDrawable roundDrawable = new RoundDrawable();
        roundDrawable.setBounds(0, 0, 100, 100);
        img.setBackground(roundDrawable);
        clipDrawable = (ClipDrawable) clip_v.getBackground();
        handler = new Handler();
        r = new Run();
        handler.postDelayed(r, 100);
    }

    boolean transition = false;

    private class Run implements Runnable {
        int index;

        @Override
        public void run() {
            index = (index + 50) % 10000;
            clipDrawable.setLevel(index);
            handler.postDelayed(this, 100);
        }
    }

    @OnClick({R.id.transition_tv})
    public void btnOnClick(View view) {
        switch (view.getId()) {
            case R.id.transition_tv:
                if (transition) {
                    transition = false;
                    TransitionDrawable background = (TransitionDrawable) transition_tv.getBackground();
                    background.reverseTransition(300);
                } else {
                    transition = true;
                    TransitionDrawable background = (TransitionDrawable) transition_tv.getBackground();
                    background.startTransition(300);
                }


                break;
        }
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(r);
        handler = null;
        super.onDestroy();
    }
}
