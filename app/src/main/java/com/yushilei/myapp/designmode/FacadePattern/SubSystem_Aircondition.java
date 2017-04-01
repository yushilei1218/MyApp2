package com.yushilei.myapp.designmode.FacadePattern;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-15:22
 * @desc
 */

public class SubSystem_Aircondition {
    public void lightOn() {
        Log.i("SubSystem_Aircondition", "打开空调");
    }

    public void lightOff() {
        Log.i("SubSystem_Aircondition", "关闭空调");
    }
}
