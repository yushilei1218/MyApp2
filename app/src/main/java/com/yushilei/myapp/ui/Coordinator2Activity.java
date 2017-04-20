package com.yushilei.myapp.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.adapter.RecyAdapter;

import butterknife.BindView;

public class Coordinator2Activity extends BaseActivity {

    @BindView(R.id.coordinator2_recycler)
    RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_coordinator2;
    }

    @Override
    protected void onInitViews() {
        recyclerView.setAdapter(new RecyAdapter(this));
    }
}
