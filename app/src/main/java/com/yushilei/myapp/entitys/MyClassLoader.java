package com.yushilei.myapp.entitys;

import dalvik.system.PathClassLoader;

/**
 * @auther by yushilei.
 * @time 2017/5/23-14:00
 * @desc
 */

public class MyClassLoader extends PathClassLoader {
    private static String classPath = System.getProperty("java.class.path", ".");

    public MyClassLoader() {
        super(classPath, ClassLoader.getSystemClassLoader());
    }

    public MyClassLoader(String dexPath, String libraryPath, ClassLoader parent) {
        super(dexPath, libraryPath, parent);
    }
}
