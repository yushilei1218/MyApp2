package com.yushilei.myapp.entitys.filter;

/**
 * @auther by yushilei.
 * @time 2017/3/29-16:01
 * @desc
 */

public class StationAddress extends Address {
    private int jingdu;
    private int weidu;

    public StationAddress(String name) {
        super(name);
    }

    public int getJingdu() {
        return jingdu;
    }

    public void setJingdu(int jingdu) {
        this.jingdu = jingdu;
    }

    public int getWeidu() {
        return weidu;
    }

    public void setWeidu(int weidu) {
        this.weidu = weidu;
    }
}
