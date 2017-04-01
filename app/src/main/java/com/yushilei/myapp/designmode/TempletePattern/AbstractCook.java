package com.yushilei.myapp.designmode.TempletePattern;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-14:41
 * @desc
 */

public abstract class AbstractCook {
    public void cookProcess() {
        firstOpenFire();
        secondAddOil();
        addVegetable();
        cookVegetable();
        four();
    }

    private void firstOpenFire() {
        Log.i("AbstractCook", "AbstractCook 点火");
    }

    private void secondAddOil() {
        Log.i("AbstractCook", "AbstractCook 加油");
    }

    public abstract void addVegetable();

    public abstract void cookVegetable();

    private void four() {
        Log.i("AbstractCook", "AbstractCook 装盘");
    }
}
