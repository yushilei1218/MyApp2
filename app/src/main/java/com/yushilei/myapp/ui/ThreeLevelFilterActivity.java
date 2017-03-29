package com.yushilei.myapp.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.entitys.filter.Address;
import com.yushilei.myapp.entitys.filter.StationAddress;
import com.yushilei.myapp.entitys.filter.middleAddress;
import com.yushilei.myapp.util.Change;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

public class ThreeLevelFilterActivity extends BaseActivity {
    @BindView(R.id.tlf_lv)
    ListView mLv;
    @BindView(R.id.tlf_content)
    FrameLayout container;

    List<Address> data = new LinkedList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_three_level_filter;
    }

    @Override
    protected void onInitViews() {
        initData();

        String url = Change.url("http://i1.douguo.net//upload/caiku/0/c/5/0c5c2a5301cbcc86c286a8a6e5531575.jpg");
        Log.i(getTAG(), url);
        String a = "http:\\/\\/i1.douguo.net\\/\\/upload\\/caiku\\/0\\/c\\/5\\/0c5c2a5301cbcc86c286a8a6e5531575.jpg";
        String url1 = Change.url(a);
        Log.i(getTAG(), a);
        Log.i(getTAG(), url1);
        LinkedList<String> data = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            data.add("item+" + i);
        }
        mLv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));
    }

    private void initData() {
        Address toMe = new Address("距离我");
        middleAddress air = new middleAddress("机场");
        air.addAddress(new StationAddress("不限"));
        air.addAddress(new StationAddress("首都国际机场"));
        air.addAddress(new StationAddress("北京南苑机场"));
        toMe.addAddress(air);

        middleAddress train = new middleAddress("火车站");
        train.addAddress(new StationAddress("不限"));
        train.addAddress(new StationAddress("北京站"));
        train.addAddress(new StationAddress("北京南"));
        train.addAddress(new StationAddress("北京西"));
        train.addAddress(new StationAddress("北京北"));

        toMe.addAddress(train);

        middleAddress bus = new middleAddress("长途汽车站");
        bus.addAddress(new StationAddress(""));
        toMe.addAddress(bus);
        data.add(toMe);
    }

    public void showTlf(View view) {
        View tlf = LayoutInflater.from(this).inflate(R.layout.tlf_layout, container, false);
        ListView firstLv = (ListView) tlf.findViewById(R.id.tlf_first);
        ListView secondLv = (ListView) tlf.findViewById(R.id.tlf_second);
        ListView thirdLv = (ListView) tlf.findViewById(R.id.tlf_third);
    }
}
