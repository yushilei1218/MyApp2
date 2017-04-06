package com.yushilei.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.GridView;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.adapter.XfermodeAdapter;

import butterknife.BindView;

public class GuaguaKaActivity extends BaseActivity {
    @BindView(R.id.xfermode_grid)
    GridView xferGrid;

    @Override
    public int getLayoutId() {
        return R.layout.activity_guagua_ka;
    }

    @Override
    protected void onInitViews() {
        xferGrid.setAdapter(new XfermodeAdapter(this));
    }
}
