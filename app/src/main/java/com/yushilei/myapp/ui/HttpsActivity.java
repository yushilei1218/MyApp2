package com.yushilei.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.api.NetApi12306;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HttpsActivity extends BaseActivity implements Callback<String> {
    @BindView(R.id.https_tv)
    TextView tv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_https;
    }

    public void https(View view) {
        NetApi12306.service.getContent().enqueue(this);
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        Toast.makeText(this, "onResponse", Toast.LENGTH_SHORT).show();
        tv.setText(response.body());
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        Toast.makeText(this, "异常", Toast.LENGTH_SHORT).show();
        t.printStackTrace();
    }
}
