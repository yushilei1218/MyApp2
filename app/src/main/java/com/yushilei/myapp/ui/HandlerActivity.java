package com.yushilei.myapp.ui;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Binder;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.fragment.ThreadLoaclFragment;

import butterknife.BindView;

public class HandlerActivity extends BaseActivity {
    public static final ThreadLocal<MyListener> threadLocal = new ThreadLocal<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private Handler handler2 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });

    @BindView(R.id.handler_tv)
    TextView tv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_handler;
    }

    @Override
    protected void onInitViews() {

        Message msg = new Message();
        handler.sendMessage(msg);
        handler2.removeCallbacksAndMessages(null);
        threadLocal.set(new MyListener() {
            @Override
            public void update(String msg) {
                tv.setText(msg);
                Toast.makeText(HandlerActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void threadLocal(View view) {
        MyListener myListener = threadLocal.get();
        if (myListener != null) {
            myListener.update("测试一下");
        }

    }

    public void opFg(View view) {
        ThreadLoaclFragment fragment = new ThreadLoaclFragment();
        fragment.show(getSupportFragmentManager(), "");
    }

    private class Run implements Runnable {
        @Override
        public void run() {

        }
    }

    public interface MyListener {
        void update(String msg);
    }
}
