package com.yushilei.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;

import java.io.IOException;
import java.net.Socket;

public class SocketActivity extends BaseActivity {

    private Socket socket;

    @Override
    public int getLayoutId() {
        return R.layout.activity_socket;
    }

    public void socket(View view) {
        if (socket == null) {
            try {
                socket = new Socket("", 8080);
                socket.setKeepAlive(true);
                socket.sendUrgentData(0xFF);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
