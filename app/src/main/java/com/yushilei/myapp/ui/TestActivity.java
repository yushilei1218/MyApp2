package com.yushilei.myapp.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.adapter.RecyAdapter;

import butterknife.BindView;

public class TestActivity extends BaseActivity {
    @BindView(R.id.test_recycler)
    RecyclerView recyclerView;

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
    }

    public void btnTest(View view) {
        Toast.makeText(this, "Child " + "x=" + view.getX() + " top=" + view.getTop(), Toast.LENGTH_SHORT).show();
    }
}
