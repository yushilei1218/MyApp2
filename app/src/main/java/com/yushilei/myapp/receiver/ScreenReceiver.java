package com.yushilei.myapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.yushilei.myapp.services.LockScreenService;

public class ScreenReceiver extends BroadcastReceiver {
    public ScreenReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("ScreenReceiver", "onReceive :" + intent.getAction());
        switch (intent.getAction()) {
            case Intent.ACTION_SCREEN_OFF://锁屏
                Log.i("ScreenReceiver", "ACTION_SCREEN_OFF");
                Intent service = new Intent(context, LockScreenService.class);
                service.setAction("Add");
                context.startService(service);
                break;
            case Intent.ACTION_SCREEN_ON://开屏

                break;
            case Intent.ACTION_USER_PRESENT://解锁
                Intent service2 = new Intent(context, LockScreenService.class);
                service2.setAction("Remove");
                context.startService(service2);
                break;
        }
    }
}
