package com.yushilei.myapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * @auther by yushilei.
 * @time 2017/2/23-11:10
 * @desc
 */

public class FgAdapter extends FragmentPagerAdapter {

    List<Fragment> data = new LinkedList<>();


    public FgAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.data = data;
    }

    public FgAdapter(FragmentManager fm) {
        super(fm);
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
        return "Item+" + position;
    }
}
