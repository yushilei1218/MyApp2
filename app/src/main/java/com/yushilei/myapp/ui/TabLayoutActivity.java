package com.yushilei.myapp.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.adapter.FgAdapter;
import com.yushilei.myapp.fragment.PagerFragment;

import java.util.LinkedList;

import butterknife.BindView;

public class TabLayoutActivity extends BaseActivity {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.pager)
    ViewPager pager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tab_layout;
    }

    @Override
    protected void onInitViews() {
        LinkedList<Fragment> data = new LinkedList<>();
        Fragment fg = PagerFragment.instance("1");
        Fragment fg2 = PagerFragment.instance("2");
        Fragment fg3 = PagerFragment.instance("3");
        Fragment fg4 = PagerFragment.instance("4");
        Fragment fg5 = PagerFragment.instance("5");
        Fragment fg6 = PagerFragment.instance("6");
        Fragment fg7 = PagerFragment.instance("7");
        Fragment fg8 = PagerFragment.instance("8");
        data.add(fg);
        data.add(fg2);
        data.add(fg3);
        data.add(fg4);
        data.add(fg5);
        data.add(fg6);
        data.add(fg7);
        data.add(fg8);
        pager.setAdapter(new FgAdapter(getSupportFragmentManager(), data));
        tabs.setupWithViewPager(pager);
    }
}
