package com.yushilei.myapp.ui;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.widget.BezierSignatureView;

import butterknife.BindView;

public class BezierSignActivity extends BaseActivity {
    @BindView(R.id.sign_v)
    BezierSignatureView signV;
    @BindView(R.id.bitmap_container)
    View container;
    @BindView(R.id.sign_bitmap)
    ImageView img;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bezier_sign;
    }

    public void getBitmap(View view) {
        boolean b = container.getVisibility() == View.VISIBLE;
        if (b) {
            container.setVisibility(View.GONE);
        } else {
            Bitmap signBitmap = signV.getSignBitmap();
            img.setImageBitmap(signBitmap);
            container.setVisibility(View.VISIBLE);
        }
    }

    public void clear(View view) {
        signV.clear();
    }

    public void getdrawCacheBitmap(View view) {
        boolean b = container.getVisibility() == View.VISIBLE;
        if (b) {
            container.setVisibility(View.GONE);
        } else {
            Bitmap signBitmap = signV.getDrawBitmap();
            img.setImageBitmap(signBitmap);
            container.setVisibility(View.VISIBLE);
        }
    }
}
