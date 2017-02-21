package com.yushilei.myapp.util;

import java.util.List;

/**
 * @auther by yushilei.
 * @time 2017/2/21-17:17
 * @desc
 */

public class CollectionUtil {

    public static boolean isEmpty(List data) {
        return !(data != null && data.size() > 0);
    }
}
