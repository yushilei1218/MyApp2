package com.yushilei.myapp.entitys;

import android.graphics.PorterDuffXfermode;

/**
 * @auther by yushilei.
 * @time 2017/4/6-13:53
 * @desc
 */

public class XferMode {
    private PorterDuffXfermode xfermode;
    private String mode;

    public XferMode(PorterDuffXfermode xfermode, String mode) {
        this.xfermode = xfermode;
        this.mode = mode;
    }

    public PorterDuffXfermode getXfermode() {
        return xfermode;
    }

    public void setXfermode(PorterDuffXfermode xfermode) {
        this.xfermode = xfermode;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
