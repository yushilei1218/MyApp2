package com.yushilei.myapp.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.constant.Constant;
import com.yushilei.myapp.services.Book;

import java.util.List;

public class ProcessIPCActivity extends BaseActivity implements ServiceConnection {

    private boolean bind;
    private Messenger messenger;

    private Messenger getResMessenger = new Messenger(new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    msg.getData().setClassLoader(getClass().getClassLoader());
                    Book book = (Book) msg.getData().getParcelable(Constant.keyObj);
                    Log.i(getTAG(), book.toString());
                    break;
            }
            return true;
        }
    }));

    @Override
    public int getLayoutId() {
        return R.layout.activity_process_ipc;
    }

    @Override
    protected void onInitViews() {

    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.i(getTAG(), "onServiceConnected");
        bind = true;
        messenger = new Messenger(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.i(getTAG(), "onServiceDisconnected");
    }

    public void bindService(View view) {

        Intent service = new Intent();
        service.setAction(Constant.com_yushilei_myapp_action);
        service.addCategory("android.intent.category.DEFAULT");

        bindService(service, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        if (bind) {
            unbindService(this);
        }
        super.onDestroy();
    }

    public void messengerSend(View view) {
        if (bind) {
            try {
                Message message = new Message();
                message.what = 1;
                Bundle data = new Bundle();
                data.putString(Constant.key, "你好");

                data.putParcelable(Constant.keyObj, new Book("程", 16));
                message.setData(data);
                message.replyTo = getResMessenger;
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
