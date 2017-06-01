package com.yushilei.myapp.ui;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.entitys.Location;
import com.yushilei.myapp.util.GsonUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.OnClick;

public class Filter2Activity extends BaseActivity {
    @BindView(R.id.content_layout)
    ViewGroup mContentGp;
    @BindView(R.id.content)
    View content;

    @Override
    public int getLayoutId() {
        return R.layout.activity_filter2;
    }

    @OnClick({R.id.content_layout,
            R.id.btn_location,
            R.id.btn_add_remove,
            R.id.btn_test
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.content_layout:
                contentGoHideAnim();
                break;
            case R.id.btn_location:
                if (mContentGp.getVisibility() != View.VISIBLE) {
                    mContentGp.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_add_remove:
                if (mContentGp.getChildCount() > 0) {
                    mContentGp.removeView(content);
                } else {
                    mContentGp.addView(content);
                }
                break;
            case R.id.btn_test:
                Location location = GsonUtil.toObj(getJson(), Location.class);
                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private String getJson() {
        String json = null;
        InputStream inputStream = getResources().openRawResource(R.raw.json);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            json = new String(bos.toByteArray(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    private void contentGoHideAnim() {
        int count = mContentGp.getChildCount();
        if (count > 0) {
            View visibleView = null;
            for (int i = 0; i < count; i++) {
                View childAt = mContentGp.getChildAt(i);
                boolean visible = childAt.getVisibility() == View.VISIBLE;
                if (visible) {
                    visibleView = childAt;
                    break;
                }
            }
            if (visibleView != null) {
                //隐藏的动画
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.filter2_translate);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mContentGp.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                visibleView.startAnimation(animation);
            } else {
                mContentGp.setVisibility(View.GONE);
            }
        } else {
            mContentGp.setVisibility(View.GONE);
        }
    }

}
