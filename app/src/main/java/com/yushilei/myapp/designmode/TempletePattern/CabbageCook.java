package com.yushilei.myapp.designmode.TempletePattern;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-14:45
 * @desc
 */

public class CabbageCook extends AbstractCook {
    @Override
    public void addVegetable() {
        Log.i("CabbageCook", "CabbageCook 添加包菜");
    }

    @Override
    public void cookVegetable() {
        Log.i("CabbageCook", "CabbageCook 翻炒");
    }
}
