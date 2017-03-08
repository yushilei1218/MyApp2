package com.yushilei.myapp;

import android.app.Application;

import com.yushilei.myapp.BuildConfig;
import com.yushilei.myapp.db.DbUtil;

import org.xutils.x;

/**
 * @auther by yushilei.
 * @time 2017/3/8-16:37
 * @desc
 */

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        if (BuildConfig.DEBUG) {
            x.Ext.setDebug(true); // 开启debug会影响性能
        } else {
            x.Ext.setDebug(false);
        }

        DbUtil.instance();
    }
}
