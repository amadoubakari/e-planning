package com.flys.eplanning.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.flys.eplanning.R;
import com.flys.eplanning.activity.MainActivity_;
import com.flys.eplanning.tools.MyApplication;
import com.flys.eplanning.tools.Utility;

/**
 * Created by SnowFlake on 07.02.2016.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        long timeStamp = intent.getLongExtra("time_stamp", 0);
        // int color = intent.getIntExtra("color", 0);
        Log.e(getClass().getSimpleName(), "------------------  Fulldate:" + Utility.getFullDate(timeStamp) + " ----------------------");

        Intent resultIntent = new Intent(context, MainActivity_.class);

        if (MyApplication.isActivityVisible()) {
            resultIntent = intent;
        }


        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) timeStamp,
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Flys Notification");
        builder.setContentTitle(title);
        builder.setContentText(description);
        //builder.setColor(context.getResources().getColor(color));
        builder.setSmallIcon(R.drawable.tony);

        //builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setVibrate(new long[]{1000, 1500, 2000});
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmSound);
        //notification.setSound(Uri.parse("android.resource://"+ context.getPackageName() + "/" + R.raw.rihanna));
        builder.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.rihanna));
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify((int) timeStamp, notification);
    }
}
