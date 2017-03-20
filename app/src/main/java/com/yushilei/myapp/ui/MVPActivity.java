package com.yushilei.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.api.NetApi;
import com.yushilei.myapp.entitys.Update;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MVPActivity extends BaseActivity implements Callback<Update> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void onInitViews() {

    }

    public void login(View view) {
        Call<Update> update = NetApi.service.getUpdate("2.98", "I9000S");
        update.enqueue(this);
    }

    @Override
    public void onResponse(Call<Update> call, Response<Update> response) {
        if (response.code() == 200) {
            Update body = response.body();
            Log.i(getTAG(), "onResponse " + body.toString());
        }
        Log.i(getTAG(), "onResponse " + response.code());
    }

    @Override
    public void onFailure(Call<Update> call, Throwable t) {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
        t.printStackTrace();
        Log.i(getTAG(), "onFailure");
    }
}
