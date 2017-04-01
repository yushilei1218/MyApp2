package com.yushilei.myapp.designmode.ProxyPattern;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-14:29
 * @desc
 */

public class RealSubject implements Subject {
    @Override
    public void buy() {
        Log.i("RealSubject", "RealSubject 是真正购买发起者");
    }
}
