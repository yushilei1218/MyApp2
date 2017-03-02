package com.yushilei.myapp.ui;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;

import butterknife.OnClick;

public class RemoteViewsActivity extends BaseActivity {

    private RemoteViews remoteViews;

    @Override
    public int getLayoutId() {
        return R.layout.activity_remote_views;
    }

    @OnClick({
            R.id.normal_notification,
            R.id.remote_notification,
            R.id.update_notice
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.normal_notification:
                sendNotification();
                break;
            case R.id.remote_notification:
                sendRemoteViewsNotification();
                break;
            case R.id.update_notice:

                break;
        }
    }

    private void sendRemoteViewsNotification() {
        PendingIntent intent = PendingIntent.getActivity(
                this, 0, new Intent(this, RemoteViewsActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews = new RemoteViews(this.getPackageName(), R.layout.remote_view2);
        Notification notification = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.noti_1)
                .setContent(remoteViews)
                .mNotification;
        remoteViews.setTextViewText(R.id.remote_tv, "你好");
        remoteViews.setImageViewResource(R.id.remote_img, R.mipmap.noti_2);
        remoteViews.setOnClickPendingIntent(R.id.remote_view2, intent);
        notification.icon = R.mipmap.noti_1;
        NotificationManager m = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        m.notify(2, notification);
    }

    private void sendNotification() {
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, RemoteViewsActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Notification mNotification = new Notification();
        mNotification.tickerText = "你好";
        mNotification.icon = R.mipmap.ic_launcher;
        mNotification.when = System.currentTimeMillis();
        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
        mNotification.contentIntent = pendingIntent;
        mNotification.contentView = new RemoteViews(this.getPackageName(), R.layout.remote_view);

        NotificationManager m = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        m.notify(1, mNotification);

    }
}
