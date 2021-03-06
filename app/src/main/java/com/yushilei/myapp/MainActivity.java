package com.yushilei.myapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import com.yushilei.myapp.adapter.HomeAdapter;
import com.yushilei.myapp.constant.Constant;
import com.yushilei.myapp.services.WindowService;
import com.yushilei.myapp.util.BadgeUtil;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.grid)
    GridView grid;
    @BindView(R.id.round)
    ImageView img;

    @Override
    protected void onInitViews() {

        HomeAdapter adapter = new HomeAdapter(this);

        List<String> data = new LinkedList<>();
        data.add(Constant.DRAG);
        data.add(Constant.VIEW_PAGER);
        data.add(Constant.CCB);
        data.add(Constant.PAGER);
        data.add(Constant.Bezier);
        data.add(Constant.BezierSign);
        data.add(Constant.Refresh);
        data.add(Constant.Constraint);
        data.add(Constant.RemoteViews);
        data.add(Constant.Windows);
        data.add(Constant.Xutil);
        data.add(Constant.Coodinator);
        data.add(Constant.AppBarLayout);
        data.add(Constant.RxJava);
        data.add(Constant.CustomDrawable);
        data.add(Constant.Test);
        data.add(Constant.CustomRecycler);
        data.add(Constant.Socket);
        data.add(Constant.Animation);
        data.add(Constant.MVP);
        data.add(Constant.Handler);
        data.add(Constant.Process);
        data.add(Constant.ViewStub);
        data.add(Constant.RetrofitRxJava);
        data.add(Constant.Glide);
        data.add(Constant.Https);
        data.add(Constant.WebView);
        data.add(Constant.PhotoView);
        data.add(Constant.Matrix);
        data.add(Constant.Filter);
        data.add(Constant.ThreeLevelFilter);
        data.add(Constant.PageTurn);
        data.add(Constant.ClipRegion);
        data.add(Constant.DesignMode);
        data.add(Constant.GUAGUAKA);
        data.add(Constant.XuanTing);
        data.add(Constant.ClipText);
        data.add(Constant.Coordinator2);
        data.add(Constant.ClassLoader);
        data.add(Constant.Intent);
        data.add(Constant.Filter2);
        data.add(Constant.PTR);
        data.add(Constant.Camera2);
        data.add(Constant.JNI);
        data.add(Constant.RenderScript);
        grid.setAdapter(adapter);

        adapter.addAll(data);
        startService(new Intent(this, WindowService.class));

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
