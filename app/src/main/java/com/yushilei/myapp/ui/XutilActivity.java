package com.yushilei.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.db.DbUtil;
import com.yushilei.myapp.entitys.Bean;

import org.xutils.ex.DbException;

import java.util.LinkedList;
import java.util.List;

public class XutilActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_xutil;
    }

    public void btnSave(View view) {
        List<Bean> data = new LinkedList<Bean>();
        for (int i = 0; i < 5; i++) {
            Bean bean = new Bean();
            bean.setName(i + "条");
            bean.setAge(i * 10);
            data.add(bean);
        }
        try {
            DbUtil.instance().dataDb.saveBindingId(data);
        } catch (DbException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < data.size(); i++) {
            Log.i(getTAG(), data.get(i).toString());
        }
    }
}
