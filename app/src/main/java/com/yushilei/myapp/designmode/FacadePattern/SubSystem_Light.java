package com.yushilei.myapp.designmode.FacadePattern;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-15:19
 * @desc
 */

public class SubSystem_Light {
    public void lightOn() {
        Log.i("SubSystem_Light", "开灯");
    }

    public void lightOff() {
        Log.i("SubSystem_Light", "关灯");
    }
}
