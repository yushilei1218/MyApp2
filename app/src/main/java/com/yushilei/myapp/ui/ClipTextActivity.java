package com.yushilei.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.widget.ColorTransferView;

import butterknife.BindView;

public class ClipTextActivity extends BaseActivity {
    @BindView(R.id.color_transfer_v)
    ColorTransferView colorTransferView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_clip_text;
    }

    public void colorTransfer(View view) {
        colorTransferView.startAnim();
    }
}
