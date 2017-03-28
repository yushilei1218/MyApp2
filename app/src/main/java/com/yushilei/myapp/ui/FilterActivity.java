package com.yushilei.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.yushilei.myapp.adapter.SortAdapter;
import com.yushilei.myapp.adapter.StarAdapter;
import com.yushilei.myapp.entitys.QueryParams;
import com.yushilei.myapp.entitys.Sort;
import com.yushilei.myapp.entitys.Star;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.filter1)
    TextView tv1;
    @BindView(R.id.filter2)
    TextView tv2;
    @BindView(R.id.test)
    TextView test;
    @BindView(R.id.filter_lv)
    ListView lv;
    @BindView(R.id.filter_content)
    LinearLayout content;

    List<Sort> mSorts = new LinkedList<>();
    List<Star> mStars = new LinkedList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_filter;
    }

    @Override
    protected void onInitViews() {
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
    }

    private void initTv(QueryParams queryParams) {
        tv1.setText(queryParams.getSort());
        tv2.setText(queryParams.getStar());
    }

    @OnClick({
            R.id.filter_content,
            R.id.filter1,
            R.id.filter2,
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.filter1: {
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
            break;
            case R.id.filter2: {
                Toast.makeText(this, "Star", Toast.LENGTH_SHORT).show();
                showStarLayout();
            }
            break;
            case R.id.filter_content:
                content.setVisibility(View.GONE);
                break;

            case R.id.star_reset:
                Toast.makeText(this, "reset", Toast.LENGTH_SHORT).show();
                for (Star s : mStars) {
                    if (s.getName().equals("不限")) {
                        s.setSelected(true);
                    } else {
                        s.setSelected(false);
                    }
                }
                showStarLayout();
                break;
            case R.id.star_confirm:
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
                break;
        }
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
