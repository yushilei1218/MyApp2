package com.yushilei.myapp.services;

import android.animation.AnimatorListenerAdapter;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.yushilei.myapp.R;
import com.yushilei.myapp.api.NetApi;
import com.yushilei.myapp.receiver.ScreenReceiver;

import java.util.TimerTask;

/**
 * @auther by yushilei.
 * @time 2017/3/31-14:24
 * @desc
 */

public class WindowService extends Service {

    private View child;
    Handler handler = new Handler();
    private View container;
    private ScreenReceiver receiver;

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_USER_PRESENT);

        receiver = new ScreenReceiver();
        registerReceiver(receiver, filter);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, final int flags, int startId) {
        Log.i("WindowService", "onStartCommand");

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                Log.i("WindowService", "show Window");

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
                        child = LayoutInflater.from(getApplication()).inflate(R.layout.window_layout, null, false);
                        container = child.findViewById(R.id.window_container);
                        container.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(WindowService.this, "点击了Window", Toast.LENGTH_SHORT).show();
                            }
                        });
                        DisplayMetrics metrics = new DisplayMetrics();
                        wm.getDefaultDisplay().getMetrics(metrics);

                        int withSpec = View.MeasureSpec.makeMeasureSpec(metrics.widthPixels, View.MeasureSpec.EXACTLY);
                        int heightSpec = View.MeasureSpec.makeMeasureSpec(metrics.heightPixels, View.MeasureSpec.AT_MOST);
                        child.measure(withSpec, heightSpec);

                        WindowManager.LayoutParams lp =
                                new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                                        , ViewGroup.LayoutParams.WRAP_CONTENT
                                        , 0, 0, PixelFormat.TRANSPARENT);
                        lp.flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |

                                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

                        lp.gravity = Gravity.LEFT | Gravity.TOP;
                        lp.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;

                        wm.addView(child, lp);
                        TranslateAnimation animation = new TranslateAnimation(0, 0, -child.getMeasuredHeight(), 0);
                        animation.setDuration(300);
                        container.startAnimation(animation);
                    }
                });

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("WindowService", "remove Window");
                        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -child.getMeasuredHeight());
                        animation.setDuration(300);
                        container.startAnimation(animation);

                        animation.setAnimationListener(new Animation.AnimationListener() {

                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
                                wm.removeViewImmediate(child);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                    }
                }, 2000);

            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }
}
