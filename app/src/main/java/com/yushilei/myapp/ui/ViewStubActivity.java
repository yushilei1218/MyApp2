package com.yushilei.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Toast;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.OnClick;

public class ViewStubActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.view_stub_id)
    ViewStub stub;

    @Override
    public int getLayoutId() {
        return R.layout.activity_view_stub;
    }

    public void showViewStub(View view) {
        //stub.setVisibility(View.VISIBLE);
        View subView = stub.inflate();
        subView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class<?>[] classes = R.class.getClasses();
        String name = null;
        for (Class<?> aClass : classes) {
            String name1 = aClass.getName();
            String name2 = aClass.getSimpleName();
            Log.i(getTAG(), name2 + " " + name1);
            if (aClass.getSimpleName().equals("id")) {
                try {
                    Field id = aClass.getField("view_stub_layout_id");
                    name = id.getName();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        Toast.makeText(this, "  " + name, Toast.LENGTH_SHORT).show();
    }
}
