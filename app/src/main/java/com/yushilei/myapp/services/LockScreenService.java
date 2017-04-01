package com.yushilei.myapp.services;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.yushilei.myapp.R;
import com.yushilei.myapp.util.BadgeUtil;

public class LockScreenService extends Service {

    private View child;

    public LockScreenService() {
    }

    Handler handler = new Handler();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        switch (intent.getAction()) {
            case "Add":
                addLockWin();
                break;
            case "Remove":
                removeWin();
                break;
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void removeWin() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                WindowManager wm = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
                wm.removeViewImmediate(child);
            }
        }, 1000);
    }

    private void addLockWin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
                        child = LayoutInflater.from(getApplicationContext()).inflate(R.layout.lock_win_layout, null);

                        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                                , 0, 0, PixelFormat.TRANSPARENT);

                        lp.x = 0;
                        lp.y = 300;
                        lp.gravity = Gravity.TOP;
                        lp.flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

                        lp.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
                        wm.addView(child, lp);

                    }
                }, 200);
            }
        }).start();
    }
}
