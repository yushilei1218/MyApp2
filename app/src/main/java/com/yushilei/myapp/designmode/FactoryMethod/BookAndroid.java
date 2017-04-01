package com.yushilei.myapp.designmode.FactoryMethod;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-11:15
 * @desc
 */

public class BookAndroid extends Book {
    @Override
    public void showName() {
        Log.i("Book","BookAndroid");
    }
}
