package com.yushilei.myapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yushilei.myapp.BaseFragment;
import com.yushilei.myapp.R;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PagerFragment extends BaseFragment {
    private static String key = "KEY";
    @BindView(R.id.fg_tv)
    TextView tv;

    public PagerFragment() {
    }

    public static PagerFragment instance(String str) {

        PagerFragment pagerFragment = new PagerFragment();
        Bundle args = new Bundle();

        args.putString(key, str);
        pagerFragment.setArguments(args);
        return pagerFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager;
    }

    @Override
    protected void onInitViews() {
        tv.setText(getArguments().getString(key));
    }
}
