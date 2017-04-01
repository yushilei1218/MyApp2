package com.yushilei.myapp.designmode.StrategyPattern;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-11:31
 * @desc
 */

public class StrategyA extends Strategy {
    @Override
    public void show() {
        Log.i("Strategy", "StrategyA 春节活动！");
    }
}
