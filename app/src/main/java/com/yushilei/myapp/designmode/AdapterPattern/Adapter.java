package com.yushilei.myapp.designmode.AdapterPattern;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-14:01
 * @desc
 */

public class Adapter extends Adaptee implements Target {
    @Override
    public void request() {
        Log.i("Adapter", "适配器继承+实现，使得接口方法可以调用源类方法");
        specificRequest();
    }
}
