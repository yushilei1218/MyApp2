package com.yushilei.myapp.api;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * @auther by yushilei.
 * @time 2017/3/27-10:21
 * @desc
 */

public class NetApiHttpsBaidu {
    private static final String Url = "https://www.baidu.com";

    public static BaiduService service;

    static {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        Request build = builder.build();
                        return chain.proceed(build);
                    }
                })
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url)
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        return new NetApiHttps.StringBodyConverter();
                    }
                })
                .client(client)
                .build();

        service = retrofit.create(BaiduService.class);
    }

    public interface BaiduService {
        @GET("/")
        Call<String> getContent();
    }
}
