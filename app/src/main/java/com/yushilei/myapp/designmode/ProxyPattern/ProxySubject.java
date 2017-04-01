package com.yushilei.myapp.designmode.ProxyPattern;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-14:30
 * @desc
 */

public class ProxySubject implements Subject {
    @Override
    public void buy() {
        Log.i("ProxySubject", "ProxySubject 代理了buy");
        new RealSubject().buy();
    }
}
