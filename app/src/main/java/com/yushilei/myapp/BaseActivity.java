package com.yushilei.myapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * @auther by yushilei.
 * @time 2017/2/21-17:07
 * @desc
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        ButterKnife.bind(this);

        onInitViews();

        onInitDatum();
    }

    protected void onInitDatum() {

    }

    protected void onInitViews() {

    }

    public abstract int getLayoutId();
}
