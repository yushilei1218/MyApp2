package com.yushilei.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.widget.RefreshView;

import java.util.LinkedList;

import butterknife.BindView;

public class WeiChatActivity extends BaseActivity {
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.refresh)
    RefreshView refreshV;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wei_chat;
    }

    @Override
    protected void onInitViews() {
        LinkedList<String> data = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            data.add("item+" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_weichat, R.id.item_chat, data);
        lv.setAdapter(adapter);

        refreshV.setRefreshListener(new RefreshView.RefreshHelperListener() {
            @Override
            public boolean canRefresh() {
                int firstVisiblePosition = lv.getFirstVisiblePosition();
                if (firstVisiblePosition == 0 && lv.getChildAt(0).getTop() >= 0)
                    return true;
                return false;
            }
        });
    }
}
