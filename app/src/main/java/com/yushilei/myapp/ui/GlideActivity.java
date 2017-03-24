package com.yushilei.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.GlideModule;
import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;

import butterknife.BindView;

public class GlideActivity extends BaseActivity {
    @BindView(R.id.glide_img)
    ImageView img;
    @BindView(R.id.glide_img1)
    ImageView img1;
    @BindView(R.id.glide_img2)
    ImageView img2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_glide;
    }

    public void gif(View view) {
        String url = "http://img.gaoxiaogif.cn/GaoxiaoGiffiles/images/2015/06/11/tiaodaojiebingdeheshang.gif";
        String url2 = "http://p1.pstatp.com/large/166200019850062839d3";
        Glide.with(this).load(url).into(img);
        Glide.with(this).load(R.mipmap.a3).into(img1);
        Glide.with(this).load(url2).into(img2);
    }
}
