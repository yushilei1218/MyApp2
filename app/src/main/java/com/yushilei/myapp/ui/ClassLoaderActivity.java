package com.yushilei.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;

import java.net.URLClassLoader;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class ClassLoaderActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_class_loader;
    }

    @Override
    protected void onInitViews() {
        ClassLoader classLoader = this.getClassLoader();

        Log.i(getTAG(), classLoader.toString());
        ClassLoader contextClassLoader = getApplication().getApplicationContext().getClassLoader();
        Log.i(getTAG(), contextClassLoader.toString());
//        ClassLoader
////        URLClassLoader;
//        PathClassLoader;
//        DexClassLoader;
////        Thread.currentThread().setContextClassLoader();

    }

}
