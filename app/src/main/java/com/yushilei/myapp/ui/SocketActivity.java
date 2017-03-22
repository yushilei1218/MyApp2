package com.yushilei.myapp.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.constant.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketActivity extends BaseActivity implements ServiceConnection {

    private Socket socket;
    private PrintWriter writer;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.i(getTAG(), "socket read write");
                                String s = reader.readLine();
                                writer.println("啊啊啊啊啊");
                                writer.flush();
                                Log.i(getTAG(), "" + s);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            writer.println("你好啊");
                        }
                    }).start();

                    break;
            }
            return true;
        }
    });
    private BufferedReader reader;


    @Override
    public int getLayoutId() {
        return R.layout.activity_socket;
    }

    @Override
    protected void onInitViews() {
        Intent service = new Intent();
        service.setAction(Constant.com_yushilei_myapp_socket);
        service.addCategory("android.intent.category.DEFAULT");

        bindService(service, this, Context.BIND_AUTO_CREATE);
    }

    public void socket(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket == null) {
                    try {
                        socket = new Socket("localhost", 8688);
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

                        handler.sendEmptyMessage(1);
                    } catch (IOException e) {
                        e.printStackTrace();
                        SystemClock.sleep(1000);
                        Log.i(getTAG(), "Socket connect server failed retry");
                    }
                }
            }
        }).start();


    }

    @Override
    protected void onDestroy() {
        unbindService(this);
        super.onDestroy();
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
