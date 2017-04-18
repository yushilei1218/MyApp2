package com.yushilei.myapp.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.adapter.XuanTingAdapter;

import butterknife.BindView;

public class XuanTingActivity extends BaseActivity {
    @BindView(R.id.xuanting_recycler)
    RecyclerView recycler;
    @BindView(R.id.activity_xuan_ting)
    RelativeLayout container;
    @BindView(R.id.container_xuanting)
    View xuantingV;
    @BindView(R.id.xuanting_tv)
    TextView xuantingTv;
    private XuanTingAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_xuan_ting;
    }

    @Override
    protected void onInitViews() {
        xuantingV.setBackground(new ColorDrawable(Color.YELLOW));
        xuantingTv.setText("测试悬停");
        adapter = new XuanTingAdapter(this);
        recycler.setAdapter(adapter);
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int childCount = recyclerView.getChildCount();
                View child = null;
                if (childCount > 0)
                    for (int i = 0; i < childCount; i++) {
                        boolean isxuanting = ((XuanTingAdapter.VH) recyclerView.getChildAt(i).getTag()).isxuanting();
                        if (isxuanting) {
                            child = recyclerView.getChildAt(i);
                            break;
                        }
                    }
                if (child != null) {
                    Log.i(getTAG(), "childTop=" + child.getTop());
                    int childAdapterPosition = recyclerView.getChildAdapterPosition(child);
                    XuanTingAdapter.Bean bean = adapter.getBean(childAdapterPosition);
                    if (child.getTop() <= 0) {
                        xuantingTv.setText(bean.getName());
                        xuantingV.setY(0);
                    } else {
                        XuanTingAdapter.Bean previousBean = adapter.getPreviousBean(childAdapterPosition);
                        if (child.getTop() >= xuantingV.getHeight()) {
                            xuantingV.setY(0);

                        } else {
                            xuantingV.setY(child.getTop() - xuantingV.getHeight());
                        }
                        if (previousBean != null) {
                            xuantingTv.setText(previousBean.getName());
                        }
                    }

                }
            }
        });
    }


}
