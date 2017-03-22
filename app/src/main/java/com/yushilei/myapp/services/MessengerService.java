package com.yushilei.myapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;

import com.yushilei.myapp.services.Book;

import com.yushilei.myapp.constant.Constant;

public class MessengerService extends Service {
    private static final String TAG = "MessengerService";
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.i(TAG, "handleMessage" + msg.what);
            switch (msg.what) {
                case 1:

                    Bundle data = msg.getData();
                    data.setClassLoader(getClass().getClassLoader());
                    Book book = data.getParcelable(Constant.keyObj);
                    Log.i(TAG, book.toString());
                    String string = data.getString(Constant.key);
                    Log.i(TAG, string);
                    try {
                        Message message = new Message();
                        message.what = 2;
                        Bundle data1 = new Bundle();
                        data1.putParcelable(Constant.keyObj, new Book("程小", 17));
                        message.setData(data1);
                        msg.replyTo.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            return true;
        }
    });
    private Messenger messenger;

    public MessengerService() {
        Log.i(TAG, "new MessengerService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        messenger = new Messenger(handler);
        return messenger.getBinder();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }
}
