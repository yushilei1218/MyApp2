package com.yushilei.myapp.ui;


import android.util.LruCache;
import android.view.TextureView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.widget.PtrLayout;

import java.util.LinkedList;

import butterknife.BindView;

public class MyPtrActivity extends BaseActivity {
    @BindView(R.id.activity_my_ptr)
    PtrLayout ptr;
    @BindView(R.id.ptr_lv)
    ListView mLv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_ptr;
    }

    @Override
    protected void onInitViews() {

        LinkedList<String> data = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            data.add("item+" + i);
        }
        mLv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));
        ptr.setPtrRrHelper(new PtrLayout.PtrRrHelper() {
            @Override
            public boolean isCanRefresh() {
                if (mLv.getChildCount() == 0)
                    return true;
                if (mLv.getFirstVisiblePosition() == 0 && mLv.getChildAt(0).getTop() == 0)
                    return true;
                return false;
            }
        });
    }
}
