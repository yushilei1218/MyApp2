package com.yushilei.myapp.ui;


import android.util.Log;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;

public class ConstraintLayoutActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_constraint_layout;
    }

    @Override
    protected void onInitViews() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG", "Integer.MAX_VALUE=" + Integer.MAX_VALUE);
                long time1 = System.currentTimeMillis();
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    int a = 1 + 1;
                }
                long time2 = System.currentTimeMillis();
                long l = (time2 - time1) / 1000;
                Log.d("TAG", "time=" + l + "s");
            }
        }).start();
    }
}
