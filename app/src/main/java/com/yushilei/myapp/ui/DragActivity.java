package com.yushilei.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yushilei.myapp.R;

import java.util.LinkedList;

public class DragActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        ListView lv = (ListView) findViewById(R.id.lv);

        LinkedList<String> data = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            data.add("第" + i + "条");
        }
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));
    }
}
