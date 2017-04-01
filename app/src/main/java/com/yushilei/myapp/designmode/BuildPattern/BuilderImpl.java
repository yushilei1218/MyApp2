package com.yushilei.myapp.designmode.BuildPattern;

/**
 * @auther by yushilei.
 * @time 2017/4/1-14:58
 * @desc
 */

public class BuilderImpl implements Builder {
    Computer computer = new Computer();

    @Override
    public void addCPU(String cpu) {
        computer.setCpu(cpu);
    }

    @Override
    public void addDisplay(String dis) {
        computer.setDisplay(dis);
    }

    @Override
    public void addSSD(String ssd) {
        computer.setSsd(ssd);
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
