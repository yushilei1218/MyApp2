package com.yushilei.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yushilei.myapp.R;
import com.yushilei.myapp.constant.Constant;
import com.yushilei.myapp.ui.AnimationActivity;
import com.yushilei.myapp.ui.AppBarLayoutActivity;
import com.yushilei.myapp.ui.BezierActivity;
import com.yushilei.myapp.ui.BezierSignActivity;
import com.yushilei.myapp.ui.CCBActivity;
import com.yushilei.myapp.ui.ClipRegionActivity;
import com.yushilei.myapp.ui.ConstraintLayoutActivity;
import com.yushilei.myapp.ui.CoodinatorActivity;
import com.yushilei.myapp.ui.CustomDrawableActivity;
import com.yushilei.myapp.ui.CustomRecyclerLayoutActivity;
import com.yushilei.myapp.ui.DesignModeActivity;
import com.yushilei.myapp.ui.DragActivity;
import com.yushilei.myapp.ui.FilterActivity;
import com.yushilei.myapp.ui.GlideActivity;
import com.yushilei.myapp.ui.GuaguaKaActivity;
import com.yushilei.myapp.ui.HandlerActivity;
import com.yushilei.myapp.ui.HttpsActivity;
import com.yushilei.myapp.ui.MVPActivity;
import com.yushilei.myapp.ui.MatrixActivity;
import com.yushilei.myapp.ui.PageTurnActivity;
import com.yushilei.myapp.ui.PhotoViewActivity;
import com.yushilei.myapp.ui.ProcessIPCActivity;
import com.yushilei.myapp.ui.RemoteViewsActivity;
import com.yushilei.myapp.ui.RetrofitRxJavaActivity;
import com.yushilei.myapp.ui.RxjavaActivity;
import com.yushilei.myapp.ui.SocketActivity;
import com.yushilei.myapp.ui.TabLayoutActivity;
import com.yushilei.myapp.ui.TestActivity;
import com.yushilei.myapp.ui.ThreeLevelFilterActivity;
import com.yushilei.myapp.ui.ViewPagerActivity;
import com.yushilei.myapp.ui.ViewStubActivity;
import com.yushilei.myapp.ui.WebViewActivity;
import com.yushilei.myapp.ui.WeiChatActivity;
import com.yushilei.myapp.ui.WindowActivity;
import com.yushilei.myapp.ui.XuanTingActivity;
import com.yushilei.myapp.ui.XutilActivity;
import com.yushilei.myapp.widget.DragView;
import com.yushilei.myapp.widget.GuaguaCardView;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther by yushilei.
 * @time 2017/2/21-16:10
 * @desc
 */

public class HomeAdapter extends BaseAdapter implements View.OnClickListener {
    Context context;
    List<String> data = new LinkedList<>();

    public HomeAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<String> list) {
        data.clear();
        if (list != null && list.size() > 0)
            data.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
            convertView.setTag(new VH(convertView));
        }
        ((VH) convertView.getTag()).tv.setText(data.get(position));
        convertView.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            switch (((TextView) v).getText().toString()) {
                case Constant.DRAG:
                    context.startActivity(new Intent(context, DragActivity.class));
                    break;
                case Constant.VIEW_PAGER:
                    context.startActivity(new Intent(context, ViewPagerActivity.class));
                    break;
                case Constant.CCB:
                    context.startActivity(new Intent(context, CCBActivity.class));
                    break;
                case Constant.PAGER:
                    context.startActivity(new Intent(context, TabLayoutActivity.class));
                    break;
                case Constant.Bezier:
                    context.startActivity(new Intent(context, BezierActivity.class));
                    break;
                case Constant.Refresh:
                    context.startActivity(new Intent(context, WeiChatActivity.class));
                    break;
                case Constant.Constraint:
                    context.startActivity(new Intent(context, ConstraintLayoutActivity.class));
                    break;
                case Constant.RemoteViews:
                    context.startActivity(new Intent(context, RemoteViewsActivity.class));
                    break;
                case Constant.Windows:
                    context.startActivity(new Intent(context, WindowActivity.class));
                    break;
                case Constant.Xutil:
                    context.startActivity(new Intent(context, XutilActivity.class));
                    break;
                case Constant.Coodinator:
                    context.startActivity(new Intent(context, CoodinatorActivity.class));
                    break;
                case Constant.AppBarLayout:
                    context.startActivity(new Intent(context, AppBarLayoutActivity.class));
                    break;
                case Constant.RxJava:
                    context.startActivity(new Intent(context, RxjavaActivity.class));
                    break;
                case Constant.CustomDrawable:
                    context.startActivity(new Intent(context, CustomDrawableActivity.class));
                    break;
                case Constant.Test:
                    context.startActivity(new Intent(context, TestActivity.class));
                    break;
                case Constant.CustomRecycler:
                    context.startActivity(new Intent(context, CustomRecyclerLayoutActivity.class));
                    break;
                case Constant.Socket:
                    context.startActivity(new Intent(context, SocketActivity.class));
                    break;
                case Constant.Animation:
                    context.startActivity(new Intent(context, AnimationActivity.class));
                    break;
                case Constant.MVP:
                    context.startActivity(new Intent(context, MVPActivity.class));
                    break;
                case Constant.Handler:
                    context.startActivity(new Intent(context, HandlerActivity.class));
                    break;
                case Constant.Process:
                    context.startActivity(new Intent(context, ProcessIPCActivity.class));
                    break;
                case Constant.ViewStub:
                    context.startActivity(new Intent(context, ViewStubActivity.class));
                    break;
                case Constant.RetrofitRxJava:
                    context.startActivity(new Intent(context, RetrofitRxJavaActivity.class));
                    break;
                case Constant.Glide:
                    context.startActivity(new Intent(context, GlideActivity.class));
                    break;
                case Constant.Https:
                    context.startActivity(new Intent(context, HttpsActivity.class));
                    break;
                case Constant.WebView:
                    context.startActivity(new Intent(context, WebViewActivity.class));
                    break;
                case Constant.PhotoView:
                    context.startActivity(new Intent(context, PhotoViewActivity.class));
                    break;
                case Constant.Matrix:
                    context.startActivity(new Intent(context, MatrixActivity.class));
                    break;
                case Constant.Filter:
                    context.startActivity(new Intent(context, FilterActivity.class));
                    break;
                case Constant.ThreeLevelFilter:
                    context.startActivity(new Intent(context, ThreeLevelFilterActivity.class));
                    break;
                case Constant.BezierSign:
                    context.startActivity(new Intent(context, BezierSignActivity.class));
                    break;
                case Constant.PageTurn:
                    context.startActivity(new Intent(context, PageTurnActivity.class));
                    break;
                case Constant.ClipRegion:
                    context.startActivity(new Intent(context, ClipRegionActivity.class));
                    break;
                case Constant.DesignMode:
                    context.startActivity(new Intent(context, DesignModeActivity.class));
                    break;
                case Constant.GUAGUAKA:
                    context.startActivity(new Intent(context, GuaguaKaActivity.class));
                    break;
                case Constant.XuanTing:
                    context.startActivity(new Intent(context, XuanTingActivity.class));
                    break;
            }

        }
    }

    public static class VH {
        @BindView(R.id.item_1)
        TextView tv;

        public VH(View item) {
            ButterKnife.bind(this, item);
        }
    }
}
