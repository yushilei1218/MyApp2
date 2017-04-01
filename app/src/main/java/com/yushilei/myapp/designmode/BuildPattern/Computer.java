package com.yushilei.myapp.designmode.BuildPattern;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-14:57
 * @desc
 */

public class Computer {
    private String cpu;
    private String ssd;
    private String display;

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void show() {
        Log.i("Computer", "Computer :cpu " + cpu + " ssd " + ssd + " display " + display);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ssd='" + ssd + '\'' +
                ", display='" + display + '\'' +
                '}';
    }
}
