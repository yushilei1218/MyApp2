package com.yushilei.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.yushilei.myapp.adapter.HomeAdapter;
import com.yushilei.myapp.constant.Constant;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.grid)
    GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        HomeAdapter adapter = new HomeAdapter(this);

        List<String> data = new LinkedList<>();
        data.add(Constant.DRAG);
        data.add(Constant.VIEW_PAGER);
        data.add(Constant.CCB);
        data.add(Constant.PAGER);
        data.add(Constant.Bezier);
        data.add(Constant.Refresh);
        grid.setAdapter(adapter);

        adapter.addAll(data);
    }
}
