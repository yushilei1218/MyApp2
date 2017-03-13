package com.yushilei.myapp.ui;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;

import java.util.LinkedList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxjavaActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_rxjava;
    }

    public void btnRx(View view) {
        List<Integer> data = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            data.add(i);
        }
        Observable<Integer> observable = Observable.from(data);

        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(getTAG(), "onCompleted " + Thread.currentThread().getName() + " ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i(getTAG(), "onNext " + Thread.currentThread().getName() + " " + integer.toString());
            }
        };

        observable.filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                Log.i(getTAG(), "Call" + Thread.currentThread().getName());
                return integer % 2 == 0;
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);

    }
}
