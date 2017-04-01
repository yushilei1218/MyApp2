package com.yushilei.myapp.designmode.BuildPattern;

/**
 * @auther by yushilei.
 * @time 2017/4/1-15:10
 * @desc
 */

public class Director {
    public void construe(Builder builder) {
        builder.addCPU("英特尔");
        builder.addDisplay("三星");
        builder.addSSD("影驰");
    }
}
