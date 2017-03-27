package com.yushilei.myapp.ui;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.api.NetApiHttps;
import com.yushilei.myapp.api.NetApiHttpsBaidu;

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
        NetApiHttps.service.getContent().enqueue(this);
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

    public void https2(View view) {
        NetApiHttpsBaidu.service.getContent().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(HttpsActivity.this, "onResponse", Toast.LENGTH_SHORT).show();
                tv.setText(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(HttpsActivity.this, "异常", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
