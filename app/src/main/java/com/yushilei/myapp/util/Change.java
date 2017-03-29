package com.yushilei.myapp.util;

import java.io.UnsupportedEncodingException;

/**
 * @auther by yushilei.
 * @time 2017/3/29-16:23
 * @desc
 */

public class Change {
    public static String change(String data) {
        try {
            byte[] bytes = data.getBytes("UTF-8");
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String url(String data) {
        return data.replace("\\", "");
        //return data.replaceAll("\\.", "");
    }
}
