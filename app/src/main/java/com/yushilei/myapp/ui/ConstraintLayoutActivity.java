package com.yushilei.myapp.ui;


import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.widget.DragView;

import java.util.LinkedList;

public class ConstraintLayoutActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_constraint_layout;
    }

    @Override
    protected void onInitViews() {
        ListView lv = (ListView) findViewById(R.id.lv2);
        DragView viewById = (DragView) findViewById(R.id.drag2);
        viewById.setLv(lv);

        LinkedList<String> data = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            data.add("第" + i + "条");
        }
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));

    }
}
