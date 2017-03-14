package com.yushilei.myapp.ui;

import android.content.res.AssetFileDescriptor;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.db.BlackBard;
import com.yushilei.myapp.db.DbUtil;
import com.yushilei.myapp.entitys.Bean;

import org.xutils.ex.DbException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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

    public void saveDb(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DbUtil.instance().dataDb.delete(BlackBard.class);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                long time1 = System.currentTimeMillis();
                try {
                    File directory = Environment.getExternalStorageDirectory();

                    File file = new File(directory, "rmb_black_card.txt");
                    if (file.exists() && file.length() > 0) {
                        FileReader reader = new FileReader(file);
                        BufferedReader reader1 = new BufferedReader(reader);
                        List<BlackBard> data = new LinkedList<>();
                        String line;
                        int index = 0;

                        while (!TextUtils.isEmpty(line = reader1.readLine())) {

                            String trim = line.trim();
                            if (!TextUtils.isEmpty(trim) && trim.length() > 10) {
                                index++;
                                data.add(new BlackBard(trim));
                            }
                        }
                        DbUtil.instance().dataDb.save(data);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                long time2 = System.currentTimeMillis();
                float sec = (time2 - time1) / 1000f;
                long count = 0;
                try {
                    count = DbUtil.instance().dataDb.selector(BlackBard.class).count();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                Log.i(getTAG(), "耗时=" + sec + "秒,存储了" + count + "条");
            }
        }).start();

    }

    public void queryBlackCard(View view) {
        try {
            BlackBard blackBard = DbUtil.instance().dataDb.findById(BlackBard.class, "6224828661951534");
            if (blackBard != null) {
                Toast.makeText(this, blackBard.getCardNo(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "未查询到", Toast.LENGTH_SHORT).show();
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

    }
}
