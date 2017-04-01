package com.yushilei.myapp.designmode.FacadePattern;

import android.util.Log;

/**
 * @auther by yushilei.
 * @time 2017/4/1-15:21
 * @desc
 */

public class Facade {
    private SubSystem_Aircondition aircondition;
    private SubSystem_Light light;
    private SubSystem_Television television;

    public Facade(SubSystem_Aircondition aircondition, SubSystem_Light light, SubSystem_Television television) {
        this.aircondition = aircondition;
        this.light = light;
        this.television = television;
    }

    public void on() {
        Log.i("Facade", "起床");
        light.lightOn();
        aircondition.lightOn();
        television.openTelevision();
        Log.i("Facade", "on结束");
    }

    public void off() {
        Log.i("Facade", "睡觉");
        aircondition.lightOff();
        television.closeTelevision();
        light.lightOff();
        Log.i("Facade", "off结束");
    }
}
