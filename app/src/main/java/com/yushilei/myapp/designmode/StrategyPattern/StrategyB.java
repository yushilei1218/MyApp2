package com.yushilei.myapp.designmode.StrategyPattern;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-11:32
 * @desc
 */

public class StrategyB extends Strategy {
    @Override
    public void show() {
        Log.i("Strategy", "StrategyB 端午活动！");
    }
}
