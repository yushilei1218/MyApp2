package com.yushilei.myapp.api;

import android.util.Log;

import com.yushilei.myapp.entitys.Update;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @auther by yushilei.
 * @time 2017/3/20-16:57
 * @desc
 */

public class NetApi {
    public static final String URL = "http://112.126.91.89:8082";
    public static final String TAG = "NetApi";
    public static Service service;

    static {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        builder.addHeader("hello", "ttttttt");
                        Request build = builder.build();
                        return chain.proceed(build);
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        service = retrofit.create(Service.class);
    }

    public interface Service {
        @GET("/CLS/Detection")
        Call<Update> getUpdate(@Query("versioncode") String versionCode, @Query("device") String device);
    }
}
