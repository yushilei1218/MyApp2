package com.yushilei.myapp.ui;


import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.adapter.XuanTingAdapter;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.AsyncOnSubscribe;
import rx.observers.SafeSubscriber;
import rx.schedulers.Schedulers;

public class RxjavaActivity extends BaseActivity {
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.img3)
    ImageView img3;

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

    private int count = 0;

    public void btnRxMap(View view) {

        Observable<Integer> observable = Observable.just(R.mipmap.a1, R.mipmap.a2, R.mipmap.a3, R.mipmap.a4, R.mipmap.a5, R.mipmap.a6, R.mipmap.a7);
        observable
                .map(new Func1<Integer, Drawable>() {
                    @Override
                    public Drawable call(Integer integer) {
                        count++;
                        Log.i(getTAG(), "Func1 call " + Thread.currentThread().getName() + " count=" + count);
                        if (!getMainLooper().getThread().equals(Thread.currentThread()))
                            SystemClock.sleep(1000);
                        return getResources().getDrawable(integer);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Drawable>() {
                    @Override
                    public void call(Drawable o) {
                        count++;
                        Log.i(getTAG(), "Action1 call " + Thread.currentThread().getName() + " count=" + count);
                        img.setImageDrawable(o);
                    }
                });
    }

    public void btnRxSubscribe(View view) {
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Log.i(getTAG(), "OnSubscribe call=" + Thread.currentThread().getName());
                Drawable drawable = getResources().getDrawable(R.mipmap.a2);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {
                        Log.i(getTAG(), "Observer onCompleted=" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        Log.i(getTAG(), "Observer onNext=" + Thread.currentThread().getName());
                        img2.setImageDrawable(drawable);
                    }
                });
    }

    public void btnRxFlatMap(View view) {
        int count = 100;
        List<Bean> data = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            LinkedList<String> subData = new LinkedList<>();
            for (int j = 0; j < 10; j++) {
                count++;
                subData.add(count + "sub");
            }
            data.add(new Bean(i + "", subData));
        }
        Observable.from(data)
                .flatMap(new Func1<Bean, Observable<String>>() {
                    @Override
                    public Observable<String> call(Bean bean) {
                        return Observable.from(bean.data);
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(getTAG(), s);
            }
        });
    }

    public void btnRxLift(View view) {
        Observable.just(R.mipmap.noti_1).lift(new Observable.Operator<Drawable, Integer>() {
            @Override
            public Subscriber<? super Integer> call(final Subscriber<? super Drawable> subscriber) {

                return new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i(getTAG(), "Operator call 的返回值 是 Subscribe" + integer);
                        subscriber.onNext(getResources().getDrawable(integer));
                    }
                };
            }
        }).subscribe(new MySubscribe<Drawable>("MySubscribe 订阅"));
    }

    public class MySubscribe<T> extends Subscriber<T> {
        String name;

        public MySubscribe(String name) {
            this.name = name;
        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(T t) {
            Log.i(getTAG(), "MySubscribe " + name);
        }
    }

    public class Bean {
        String name;
        List<String> data;

        Bean(String name, List<String> data) {
            this.name = name;
            this.data = data;
        }
    }
}
