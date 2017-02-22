package com.yushilei.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.widget.RotateView;

import butterknife.BindView;
import butterknife.OnClick;

public class CCBActivity extends BaseActivity {

    @BindView(R.id.ratate)
    RotateView rotateView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ccb;
    }

    @Override
    protected void onInitViews() {

    }

    @OnClick({
            R.id.btn0,
            R.id.btn1,
            R.id.btn2,
            R.id.btn3,
            R.id.btn4,
            R.id.btn5,
            R.id.btn6,
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn0:
                rotateView.startAnim();
                break;
            case R.id.btn1:
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn3:
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn4:
                Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn5:
                Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn6:
                Toast.makeText(this, "6", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
