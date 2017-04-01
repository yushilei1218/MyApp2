package com.yushilei.myapp.designmode.BuildPattern;

/**
 * @auther by yushilei.
 * @time 2017/4/1-14:56
 * @desc
 */

public interface Builder {
    void addCPU(String cpu);

    void addDisplay(String dis);

    void addSSD(String ssd);

    Computer getComputer();
}
