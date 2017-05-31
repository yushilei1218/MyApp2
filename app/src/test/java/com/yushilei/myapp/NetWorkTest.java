package com.yushilei.myapp;

import com.yushilei.myapp.api.NetApi;
import com.yushilei.myapp.api.NetApiHttpsBaidu;
import com.yushilei.myapp.entitys.Update;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @auther by yushilei.
 * @time 2017/5/25-10:05
 * @desc
 */

public class NetWorkTest {
    public void setUp() {

    }

    @Test
    public void netWorkTest() {
        try {
            Response<Update> response = NetApi.service.getUpdate("2.98", "I9000S").execute();
            if (response.isSuccessful()) {
                System.out.println("s");
                System.out.println(response.body());
            } else {
                System.out.println("f");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void netWorkTest2() {

        NetApiHttpsBaidu.service.getContent().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                System.out.println("s");
                System.out.println(response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                System.out.println("f");
            }
        });

    }
}
