package com.yushilei.myapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yushilei.myapp.R;
import com.yushilei.myapp.entitys.filter2.Location;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @auther by yushilei.
 * @time 2017/6/1-15:05
 * @desc
 */

public class LocationFilterLayout extends RelativeLayout {
    public LocationFilterLayout(Context context) {
        this(context, null);
    }

    public LocationFilterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        View innerView = LayoutInflater.from(context).inflate(R.layout.location_filter_layout, this, false);
        addView(innerView);
        ButterKnife.bind(this, innerView);

    }

    @BindView(R.id.filter_lv1)
    ListView mLv1;
    @BindView(R.id.filter_lv2)
    ListView mLv2;
    @BindView(R.id.filter_lv3)
    ListView mLv3;

    @OnClick({
            R.id.filter_reset,
            R.id.filter_ok
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.filter_reset:
                Toast.makeText(getContext(), "重置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.filter_ok:
                Toast.makeText(getContext(), "完成", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void addData() {

    }

}
