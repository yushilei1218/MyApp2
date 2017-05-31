package com.yushilei.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;

/**
 * @auther by yushilei.
 * @time 2017/2/21-17:07
 * @desc
 */

public abstract class BaseActivity extends AppCompatActivity {
    public BaseActivity() {
        Log.i("BaseActivity", "BaseActivity 构造函数呗调用");
    }

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

    public String getTAG() {
        return this.getLocalClassName();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        Activity parent = getParent();
        if (parent == null) {
            Log.i(getTAG(), "startActivityForResult 无Parent");
        } else {
            Log.i(getTAG(), "startActivityForResult Parent：" + parent);
        }
        super.startActivityForResult(intent, requestCode, options);
    }
}
