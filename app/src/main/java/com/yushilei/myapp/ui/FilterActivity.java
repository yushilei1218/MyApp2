package com.yushilei.myapp.ui;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.adapter.LocationAdapter;
import com.yushilei.myapp.adapter.SortAdapter;
import com.yushilei.myapp.adapter.StarAdapter;
import com.yushilei.myapp.entitys.Location;
import com.yushilei.myapp.entitys.QueryParams;
import com.yushilei.myapp.entitys.Sort;
import com.yushilei.myapp.entitys.Star;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FilterActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.filter1)
    TextView sortTv;
    @BindView(R.id.filter2)
    TextView startTv;
    @BindView(R.id.filter3)
    TextView locationTv;

    @BindView(R.id.test)
    TextView test;
    @BindView(R.id.filter_lv)
    ListView lv;
    @BindView(R.id.filter_content)
    LinearLayout content;

    List<Sort> mSorts = new LinkedList<>();
    List<Star> mStars = new LinkedList<>();
    private List<Location> mLocations = new LinkedList<>();
    private View locationView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_filter;
    }

    @Override
    protected void onInitViews() {
        locationTv.setText("位置信息");
        initParams();
        QueryParams queryParams = new QueryParams();
        queryParams.setSort("推荐排序");
        queryParams.setStar("星级价格");
        initTv(queryParams);
        LinkedList<String> data = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            data.add("item+" + i);
        }
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));
        test.setSelected(true);
    }

    private void initParams() {
        mSorts.add(new Sort("推荐排序", true));
        mSorts.add(new Sort("价格优先", false));
        mSorts.add(new Sort("高价优先", false));
        mSorts.add(new Sort("好评优先", false));

        mStars.add(new Star("不限", true));
        mStars.add(new Star("客栈公寓"));
        mStars.add(new Star("经济连锁"));
        mStars.add(new Star("二星其他"));
        mStars.add(new Star("三星舒适"));
        mStars.add(new Star("四星高档"));
        mStars.add(new Star("五星豪华"));

        Location trade = new Location("距离我");
        trade.getSubLocations().add(new Location("不限"));
        trade.getSubLocations().add(new Location("500米内"));
        trade.getSubLocations().add(new Location("1公里内"));
        trade.getSubLocations().add(new Location("2公里内"));
        trade.getSubLocations().add(new Location("3公里内"));
        trade.getSubLocations().add(new Location("4公里内"));
        trade.getSubLocations().add(new Location("5公里内"));
        trade.getSubLocations().add(new Location("10公里内"));
        trade.getSubLocations().add(new Location("15公里内"));
        trade.getSubLocations().add(new Location("20公里内"));
        mLocations.add(trade);

        Location region = new Location("行政区");

        region.getSubLocations().add(new Location("不限"));
        region.getSubLocations().add(new Location("朝阳区"));
        region.getSubLocations().add(new Location("丰台区"));
        region.getSubLocations().add(new Location("大兴区"));
        region.getSubLocations().add(new Location("海淀区"));
        mLocations.add(region);

        Location field = new Location("大学");
        field.getSubLocations().add(new Location("不限"));
        field.getSubLocations().add(new Location("清华大学"));
        field.getSubLocations().add(new Location("北京大学"));
        field.getSubLocations().add(new Location("人民大学"));
        field.getSubLocations().add(new Location("邮电大学"));
        field.getSubLocations().add(new Location("外语大学"));
        field.getSubLocations().add(new Location("经贸大学"));
        field.getSubLocations().add(new Location("纺织大学"));
        Location edu = new Location("二外大学");
        edu.setShowTag(true);
        field.getSubLocations().add(new Location("传媒大学"));
        field.getSubLocations().add(new Location("中国大学"));
        field.getSubLocations().add(new Location("外国大学"));
        field.getSubLocations().add(edu);
        field.getSubLocations().add(new Location("民族大学"));
        field.getSubLocations().add(new Location("知名大学"));
        field.setShowTag(true);

        mLocations.add(field);
    }

    private void initTv(QueryParams queryParams) {
        sortTv.setText(queryParams.getSort());
        startTv.setText(queryParams.getStar());
    }

    @OnClick({
            R.id.filter_content,
            R.id.filter1,
            R.id.filter2,
            R.id.filter3
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.filter1:
                showSortLayout();
                break;
            case R.id.filter2:
                showStarLayout();
                break;
            case R.id.filter3:
                showLocationLayout();
                break;
            case R.id.filter_content:
                content.setVisibility(View.GONE);
                break;
            case R.id.star_reset:
                resetStarLayout();
                break;
            case R.id.star_confirm:
                starLayoutConfirm();
                break;
        }
    }

    private void showLocationLayout() {
        content.removeAllViews();
        content.setVisibility(View.VISIBLE);
        if (locationView == null) {
            locationView = LayoutInflater.from(this).inflate(R.layout.loaction_layout, content, false);
            final ListView lv1 = (ListView) locationView.findViewById(R.id.location_lv1);
            LocationAdapter adapter = new LocationAdapter(this);

            lv1.setAdapter(adapter);
            adapter.addAll(mLocations);

            ListView lv2 = (ListView) locationView.findViewById(R.id.location_lv2);
            lv2.setAdapter(new LocationAdapter(this));
            adapter.callLv(lv2);

            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < mLocations.size(); i++) {
                        Location location = mLocations.get(i);
                        if (location.isShowTag()) {
                            ((ListView) locationView.findViewById(R.id.location_lv1)).getChildAt(i).performClick();
                            if (location.getSubLocations().size() > 0) {
                                for (int j = 0; j < location.getSubLocations().size(); j++) {
                                    Location pos = location.getSubLocations().get(j);
                                    if (pos.isShowTag()) {
                                        ((ListView) locationView.findViewById(R.id.location_lv2)).smoothScrollToPosition(j);
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }


                }
            });
        }

        content.addView(locationView);


    }

    private void starLayoutConfirm() {
        for (Star s : mStars) {
            if (s.isCacheSelected()) {
                s.setSelected(true);
            } else {
                s.setSelected(false);
            }
        }
        content.removeAllViews();
        content.setVisibility(View.GONE);
        StringBuilder sb = new StringBuilder();
        for (Star s : mStars) {
            if (s.isSelected()) {
                sb.append(s.getName()).append(" ");
            }
        }
        Toast.makeText(this, "confirm :" + sb.toString(), Toast.LENGTH_SHORT).show();
    }

    private void resetStarLayout() {
        Toast.makeText(this, "reset", Toast.LENGTH_SHORT).show();
        for (Star s : mStars) {
            if (s.getName().equals("不限")) {
                s.setSelected(true);
            } else {
                s.setSelected(false);
            }
        }
        showStarLayout();
    }

    private void showSortLayout() {
        Toast.makeText(this, "Sort", Toast.LENGTH_SHORT).show();
        content.setVisibility(View.VISIBLE);
        content.removeAllViews();
        View sortLayout = LayoutInflater.from(this).inflate(R.layout.sort_layout, content, false);
        ListView lv = (ListView) sortLayout.findViewById(R.id.sort_lv);
        SortAdapter adapter = new SortAdapter(this);
        lv.setAdapter(adapter);
        adapter.addAll(mSorts);
        content.addView(sortLayout);
    }

    private void showStarLayout() {
        content.setVisibility(View.VISIBLE);
        content.removeAllViews();
        View starLayout = LayoutInflater.from(this).inflate(R.layout.star_layout, content, false);
        GridView grid = (GridView) starLayout.findViewById(R.id.star_grid);
        View confirm = starLayout.findViewById(R.id.star_confirm);
        View reset = starLayout.findViewById(R.id.star_reset);
        reset.setOnClickListener(this);
        confirm.setOnClickListener(this);
        StarAdapter adapter = new StarAdapter(this);
        grid.setAdapter(adapter);
        adapter.addALl(mStars);
        content.addView(starLayout);
    }
}
