package com.example.root.ayo_alpha;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by axellageraldinc on 9/10/16.
 */
public class AlarmReceiver extends BroadcastReceiver {

    DatabaseHandler db;
    Event event;

    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minutes = now.get(Calendar.MINUTE);
        String clock = String.valueOf(hour) + ":" + String.valueOf(minutes);
        Cursor cursor = db.getMinId();
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        event = db.getEvent(id);
        String time = event.getTime();
        if(clock == time) {
            NotificationCompat.Builder mBuilder =
                    (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle(context.getResources().getString(R.string.message_box_title))
                            .setContentText(context.getResources().getString(R.string.message_timesheet_not_up_to_date));
            Intent resultIntent = new Intent(context, MainActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());
        }
    }
}
