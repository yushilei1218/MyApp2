package com.yushilei.myapp.designmode.AdapterPattern;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-14:12
 * @desc
 */

public class Adapter2 implements Target {
    private Adaptee adaptee;

    public Adapter2(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        Log.i("Adapter2", "适配器委派+实现，使得接口方法可以调用源类方法");
        adaptee.specificRequest();
    }
}
