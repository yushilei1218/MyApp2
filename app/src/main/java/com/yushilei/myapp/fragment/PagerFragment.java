package com.yushilei.myapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yushilei.myapp.BaseFragment;
import com.yushilei.myapp.R;
import com.yushilei.myapp.adapter.RecyAdapter;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PagerFragment extends BaseFragment {
    private static String key = "KEY";

    @BindView(R.id.pager_recycler)
    RecyclerView recyclerView;

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
        recyclerView.setAdapter(new RecyAdapter(getContext()));
    }
}
