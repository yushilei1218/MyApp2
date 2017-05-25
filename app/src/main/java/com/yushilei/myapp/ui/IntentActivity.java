package com.yushilei.myapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;

import butterknife.OnClick;

public class IntentActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_intent;
    }

    @OnClick({
            R.id.intent_call,
            R.id.intent_map
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.intent_call:
                callIntent();
                break;
            case R.id.intent_map:
                callMap();
                break;
        }
    }

    private void callMap() {
        Uri uri = Uri.parse("baidumap://map/show?center=40.057406655722,116.29644071728&zoom=1&traffic=on&bounds=37.8608310000,112.5963090000,42.1942670000,118.9491260000");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void callIntent() {
        Uri num = Uri.parse("tel:18310811571");
        Intent intent = new Intent(Intent.ACTION_DIAL, num);
        startActivity(intent);
    }
}
