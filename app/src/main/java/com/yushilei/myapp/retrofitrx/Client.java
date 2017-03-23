package com.yushilei.myapp.retrofitrx;


import com.yushilei.myapp.entitys.WeatherData;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * @auther by yushilei.
 * @time 2017/3/23-15:33
 * @desc
 */

public class Client {
    private static Retrofit retrofit;
    private static Client client;
    //http://www.weather.com.cn/data/cityinfo/101010100.html
    private Client() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        return chain.proceed(chain.request());
                    }
                })
                .build();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://www.weather.com.cn")
                .build();
    }

    public static synchronized RxApi api() {
        if (client == null) {
            client = new Client();
        }
        return retrofit.create(RxApi.class);
    }

    public interface RxApi {
        @GET("/data/cityinfo/101010100.html")
        Observable<WeatherData> getWeather();
    }
}
