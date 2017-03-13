package com.yushilei.myapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yushilei.myapp.fragment.PagerFragment;

import java.util.LinkedList;
import java.util.List;

/**
 * @auther by yushilei.
 * @time 2017/3/13-16:15
 * @desc
 */

public class MPageAdapter extends FragmentPagerAdapter {
    List<Fragment> data = new LinkedList<>();

    public MPageAdapter(FragmentManager fm) {
        super(fm);
        data.add(new PagerFragment());
        data.add(new PagerFragment());
        data.add(new PagerFragment());

    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "item+" + position;
    }
}
