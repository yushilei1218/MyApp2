package com.yushilei.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.widget.MatrixView;

import butterknife.BindView;

public class MatrixActivity extends BaseActivity {
    @BindView(R.id.matrix_v)
    MatrixView matrixView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_matrix;
    }

    @Override
    protected void onInitViews() {
        matrixView.setBitmap(R.mipmap.photo,null);
    }
}
