package com.yushilei.myapp.entitys;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * @auther by yushilei.
 * @time 2017/3/27-11:17
 * @desc
 */

public class AndroidToJs {
    Context context;

    public AndroidToJs(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void jsCallAndroid(String msg) {
        Log.i("jsCallAndroid", msg);
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
