package com.yushilei.myapp.designmode.FactoryMethod;

import android.util.Log;

import com.yushilei.myapp.services.*;

/**
 * @auther by yushilei.
 * @time 2017/4/1-11:14
 * @desc
 */

public class BookJava extends Book {
    @Override
    public void showName() {
        Log.i("Book", "BookJava");
    }
}
