package com.yushilei.myapp.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.adapter.MPageAdapter;
import com.yushilei.myapp.adapter.RecyAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class AppBarLayoutActivity extends BaseActivity {

    @BindView(R.id.app_bar_tabs)
    TabLayout tabs;
    @BindView(R.id.app_bar_pager)
    ViewPager pager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_app_bar_layout;
    }

    @Override
    protected void onInitViews() {
        pager.setAdapter(new MPageAdapter(getSupportFragmentManager()));
        tabs.setupWithViewPager(pager);
    }

    @OnClick({
            R.id.app_bar_float
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.app_bar_float:
                Toast.makeText(this, "Float", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
