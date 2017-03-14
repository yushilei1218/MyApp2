package com.yushilei.myapp.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.widget.RoundDrawable;

import butterknife.BindView;

public class CustomDrawableActivity extends BaseActivity {
    @BindView(R.id.custom_drawable_img)
    ImageView img;
    @BindView(R.id.custom_drawable_img2)
    ImageView img2;
    @BindView(R.id.custom_drawable_tv)
    TextView tv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_drawable;
    }

    @Override
    protected void onInitViews() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a2);
        RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        bitmapDrawable.setCornerRadius(20);
        img2.setImageDrawable(bitmapDrawable);

        tv.setBackground(bitmapDrawable);

        RoundDrawable roundDrawable = new RoundDrawable();
        roundDrawable.setBounds(0, 0, 100, 100);
        img.setImageDrawable(roundDrawable);
    }
}
