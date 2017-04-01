package com.yushilei.myapp.designmode.FactorySimple;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-10:21
 * @desc
 */

public class BMW extends Car {
    @Override
    public void run() {
        Log.i("Car", "BMW run()");
    }
}
