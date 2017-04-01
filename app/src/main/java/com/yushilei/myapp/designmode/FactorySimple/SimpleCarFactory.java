package com.yushilei.myapp.designmode.FactorySimple;

/**
 * @auther by yushilei.
 * @time 2017/4/1-10:21
 * @desc
 */

public class SimpleCarFactory {
    public static Car createCar(String type) {
        Car car = null;
        switch (type) {
            case "BMW":
                car = new BMW();
                break;
            case "Benz":
                car = new Benz();
                break;
        }
        return car;
    }
}
