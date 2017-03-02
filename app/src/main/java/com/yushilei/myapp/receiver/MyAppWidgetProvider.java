package com.yushilei.myapp.receiver;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.yushilei.myapp.R;

/**
 * @auther by yushilei.
 * @time 2017/3/2-15:16
 * @desc
 */

public class MyAppWidgetProvider extends AppWidgetProvider {
    private static final String ACTION = "com.yushilei.chapter5.action";
    private static final String TAG = "MyAppWidgetProvider";

    public MyAppWidgetProvider() {
        super();
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.i(TAG, "onReceive=" + intent.getAction());

        Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();

        if (intent.getAction().equals(ACTION)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    AppWidgetManager m = AppWidgetManager.getInstance(context);
                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.noti_2);
                    for (int i = 0; i < 60; i++) {
                        float degree = (i * 10) % 360;
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
                        remoteViews.setImageViewBitmap(R.id.widget_img, rotateBitmap(bitmap, degree));
                        Intent intent2 = new Intent();
                        intent2.setAction(ACTION);
                        remoteViews.setOnClickPendingIntent(R.id.widget_img, PendingIntent.getBroadcast(context
                                , 0, intent2, 0));
                        m.updateAppWidget(new ComponentName(context, MyAppWidgetProvider.class), remoteViews);
                        SystemClock.sleep(30);
                    }
                }
            }).start();
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i(TAG, "onUpdate");
        final int length = appWidgetIds.length;
        for (int i = 0; i < length; i++) {
            int appWidgetId = appWidgetIds[i];
            onUpdateWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private void onUpdateWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        Log.i(TAG, "onUpdate  " + appWidgetId);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        Intent intent = new Intent(ACTION);
        PendingIntent intent1 = PendingIntent.getBroadcast(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.widget_img, intent1);
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    private Bitmap rotateBitmap(Bitmap bitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bitmap;
    }
}
