package com.yushilei.myapp.designmode.FacadePattern;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-15:20
 * @desc
 */

public class SubSystem_Television {
    public void openTelevision() {
        Log.i("SubSystem_Television", "打开电视");
    }

    public void closeTelevision() {
        Log.i("SubSystem_Television", "关闭电视");
    }
}
