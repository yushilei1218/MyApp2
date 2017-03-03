package com.yushilei.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;

import butterknife.BindView;

public class BezierActivity extends BaseActivity {
    @BindView(R.id.numberPicker)
    NumberPicker numberPicker;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bezier;
    }

    @Override
    protected void onInitViews() {
        String[] citys = new String[]{"北京", "上海", "深圳", "乌鲁木齐", "石家庄", "哈尔滨", "武汉",};
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(citys.length - 1);
        numberPicker.setDisplayedValues(citys);
    }
}
