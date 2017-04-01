package com.yushilei.myapp.designmode.singleton;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-10:17
 * @desc
 */

public class Singleton {
    private static Singleton instance = new Singleton();

    public static Singleton newInstance() {
        return instance;
    }

    public void show() {
        Log.i("Singleton", "饿汉式");
    }
}
