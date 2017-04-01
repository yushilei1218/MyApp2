package com.yushilei.myapp.designmode.singleton;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-10:53
 * @desc
 */

public class Singleton2 {
    private static Singleton2 instance;

    public static Singleton2 newInstance() {
        synchronized (Singleton2.class) {
            if (instance == null)
                instance = new Singleton2();
        }
        return instance;
    }

    public void show2() {
        Log.i("Singleton2", "懒汉式");
    }
}
