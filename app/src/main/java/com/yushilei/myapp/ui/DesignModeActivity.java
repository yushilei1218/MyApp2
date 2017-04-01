package com.yushilei.myapp.ui;

import android.widget.ListView;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.designmode.AdapterPattern.Adaptee;
import com.yushilei.myapp.designmode.AdapterPattern.Adapter;
import com.yushilei.myapp.designmode.AdapterPattern.Adapter2;
import com.yushilei.myapp.designmode.BuildPattern.BuilderImpl;
import com.yushilei.myapp.designmode.BuildPattern.Director;
import com.yushilei.myapp.designmode.FacadePattern.Facade;
import com.yushilei.myapp.designmode.FacadePattern.SubSystem_Aircondition;
import com.yushilei.myapp.designmode.FacadePattern.SubSystem_Light;
import com.yushilei.myapp.designmode.FacadePattern.SubSystem_Television;
import com.yushilei.myapp.designmode.FactoryMethod.Book;
import com.yushilei.myapp.designmode.FactoryMethod.FactoryAndroid;
import com.yushilei.myapp.designmode.FactoryMethod.FactoryJava;
import com.yushilei.myapp.designmode.FactorySimple.Car;
import com.yushilei.myapp.designmode.FactorySimple.SimpleCarFactory;
import com.yushilei.myapp.designmode.ProxyPattern.ProxySubject;
import com.yushilei.myapp.designmode.StrategyPattern.ContextSaleMan;
import com.yushilei.myapp.designmode.TempletePattern.CabbageCook;
import com.yushilei.myapp.designmode.TempletePattern.MeatCook;
import com.yushilei.myapp.designmode.singleton.Singleton;
import com.yushilei.myapp.designmode.singleton.Singleton2;

public class DesignModeActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_design_mode;
    }

    @Override
    protected void onInitViews() {
        //单例模式-饿汉
        Singleton singleton = Singleton.newInstance();
        singleton.show();
        //单例模式-懒汉式
        Singleton2 singleton2 = Singleton2.newInstance();
        singleton2.show2();
        //简单工厂模式
        Car bmw = SimpleCarFactory.createCar("BMW");
        bmw.run();
        Car benz = SimpleCarFactory.createCar("Benz");
        benz.run();
        //工厂模式
        Book android = new FactoryAndroid().createBook();
        android.showName();
        Book java = new FactoryJava().createBook();
        java.showName();
        //策略模式
        new ContextSaleMan("春节").showStrategy();
        new ContextSaleMan("端午").showStrategy();
        //适配器模式
        new Adapter().request(); //Adapter 继承Adaptee 实现Target接口
        new Adapter2(new Adaptee()).request();//Adapter 委派 Adaptee 实现Target接口完成request调用
        //代理模式
        new ProxySubject().buy();
        //模板模式
        new CabbageCook().cookProcess();
        new MeatCook().cookProcess();
        //建造者 Builder
        BuilderImpl builder = new BuilderImpl();
        new Director().construe(builder);
        builder.getComputer().show();
        //外观模式  Facade API供外部调用 其内部集成 子System调用
        Facade facade = new Facade(new SubSystem_Aircondition(), new SubSystem_Light(), new SubSystem_Television());
        facade.on();
        facade.off();

    }
}
