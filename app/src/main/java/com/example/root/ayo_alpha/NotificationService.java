package com.example.root.ayo_alpha;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

/**
 * Created by axellageraldinc on 9/10/16.
 */
public class NotificationService extends IntentService {

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void onHandleIntent(Intent intent) {
        Notification.Builder builder = new Notification.Builder(NotificationService.this);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,notificationIntent, 0);
        builder.setSmallIcon(R.drawable. notification_template_icon_bg)
                .setContentTitle("Music player")
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = builder.getNotification();
        notificationManager.notify(R.drawable.notification_template_icon_bg, notification);
    }
}