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
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7);
        Subscriber<Integer> s = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i(getTAG(), "Subscriber onNext=" + Thread.currentThread().getName() + " " + integer);
            }
        };
        observable.filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                Log.i(getTAG(), "Func1 call=" + Thread.currentThread().getName() + " " + integer);

                return integer % 2 == 1;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s);
    }
}
