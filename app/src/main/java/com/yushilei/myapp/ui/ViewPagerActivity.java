package com.yushilei.myapp.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.adapter.BannerAdapter;
import com.yushilei.myapp.pagetransformer.RotateTransformer;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

public class ViewPagerActivity extends BaseActivity {
    @BindView(R.id.pager)
    ViewPager pager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_view_pager;
    }

    @Override
    protected void onInitViews() {
        pager.setOffscreenPageLimit(3);
        pager.setPageTransformer(false, new RotateTransformer());
        BannerAdapter adapter = new BannerAdapter(this);
        pager.setAdapter(adapter);
        List<Integer> data = new LinkedList<>();
        data.add(R.mipmap.a1);
        data.add(R.mipmap.a2);
        data.add(R.mipmap.a3);
        data.add(R.mipmap.a4);
        data.add(R.mipmap.a5);
        data.add(R.mipmap.a6);
        data.add(R.mipmap.a7);

        adapter.addAll(data);
    }
}
