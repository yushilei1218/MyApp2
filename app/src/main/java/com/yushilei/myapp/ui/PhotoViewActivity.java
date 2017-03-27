package com.yushilei.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bm.library.PhotoView;
import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;

import butterknife.BindView;

public class PhotoViewActivity extends BaseActivity {
    @BindView(R.id.photo)
    PhotoView photoView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_photo_view;
    }

    @Override
    protected void onInitViews() {
        photoView.enable();
        photoView.setMaxScale(1.0f);
    }
}
