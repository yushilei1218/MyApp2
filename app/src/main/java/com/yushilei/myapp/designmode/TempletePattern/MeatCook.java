package com.yushilei.myapp.designmode.TempletePattern;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-14:46
 * @desc
 */

public class MeatCook extends AbstractCook {
    @Override
    public void addVegetable() {
        Log.i("MeatCook", "MeatCook 添加牛肉");
    }

    @Override
    public void cookVegetable() {
        Log.i("MeatCook", "MeatCook 水煮");
    }
}
