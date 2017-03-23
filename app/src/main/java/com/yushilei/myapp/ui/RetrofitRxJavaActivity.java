package com.yushilei.myapp.ui;


import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.entitys.WeatherData;
import com.yushilei.myapp.retrofitrx.Client;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RetrofitRxJavaActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_retrofit_rx_java;
    }

    @Override
    protected void onInitViews() {

    }

    private void onFailed(Throwable throwable) {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
        throwable.printStackTrace();
    }

    private void onSuccess(WeatherData weatherData) {
        Toast.makeText(this, weatherData.getWeatherinfo().toString(), Toast.LENGTH_SHORT).show();
        Log.i(getTAG(), weatherData.getWeatherinfo().toString());
    }

    public void retrofitRequest(View view) {
        Observable<WeatherData> weather = Client.api().getWeather();
        weather.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeatherData>() {
                    @Override
                    public void call(WeatherData weatherData) {
                        onSuccess(weatherData);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        onFailed(throwable);
                    }
                });
    }
}
