package com.yushilei.myapp.util;

import com.google.gson.Gson;

/**
 * @auther by yushilei.
 * @time 2017/6/1-16:14
 * @desc
 */

public class GsonUtil {
    public static <T> T toObj(String json, Class<T> tClass) {
        return new Gson().fromJson(json, tClass);
    }
}
