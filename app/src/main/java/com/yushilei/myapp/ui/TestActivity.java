package com.yushilei.myapp.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.adapter.RecyAdapter;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends BaseActivity {
    @BindView(R.id.test_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.test_lv)
    ListView lv;

    @BindView(R.id.test_img)
    ImageView img;
    private ObjectAnimator animator;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void onInitViews() {


        recyclerView.setAdapter(new RecyAdapter(this));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

                int childCount = parent.getChildCount();
                if (childCount > 0) {
                    for (int i = 0; i < childCount; i++) {
                        View child = parent.getChildAt(i);
                        int bottom = child.getBottom();
                        paint.setColor(Color.BLUE);
                        c.drawRect(0, bottom, parent.getWidth(), bottom + 20, paint);
                    }
                }
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0, 0, 0, 20);
            }
        });

        LinkedList<String> data = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            data.add("item+" + i);
        }
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item_recycler, R.id.item_tv, data));
        Pair<String, String> o = new Pair<>("a", "b");
        SparseArray<String> array = new SparseArray<>();
        array.append(1, "ac");


    }

    @OnClick(R.id.test_img)
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.test_img:
                startAnim();
                break;
        }
    }

    private boolean isAnim = false;

    private void startAnim() {
        if (isAnim) {
            animator.cancel();
        }
        boolean selected = img.isSelected();

        AnimatorListenerAdapter listener = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnim = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnim = false;
            }
        };
        if (selected) {
            img.setSelected(false);
            animator = ObjectAnimator.ofFloat(img, "Rotation", 90, 0);

        } else {
            img.setSelected(true);
            animator = ObjectAnimator.ofFloat(img, "Rotation", 0, 90);
        }
        animator.setDuration(1000);
        animator.addListener(listener);
        animator.start();
    }

    public void btnTest(View view) {
        Toast.makeText(this, "Child " + "x=" + view.getX() + " top=" + view.getTop(), Toast.LENGTH_SHORT).show();
    }
}
