package com.yushilei.myapp.ui;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.adapter.RecyAdapter;

import butterknife.BindView;

public class AppBarLayoutActivity extends BaseActivity {
    @BindView(R.id.app_bar_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.app_bar_tabs)
    TabLayout tabs;

    @Override
    public int getLayoutId() {
        return R.layout.activity_app_bar_layout;
    }

    @Override
    protected void onInitViews() {
        recyclerView.setAdapter(new RecyAdapter(this));
        TabLayout.Tab tab = tabs.newTab();
        TabLayout.Tab tab1 = tabs.newTab();
        TabLayout.Tab tab2 = tabs.newTab();
        tab.setText("item1");
        tab1.setText("item2");
        tab2.setText("item3");
        tabs.addTab(tab);
        tabs.addTab(tab1);
        tabs.addTab(tab2);
    }
}
