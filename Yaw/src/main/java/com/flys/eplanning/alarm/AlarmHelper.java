package com.flys.eplanning.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.flys.eplanning.entities.ReminderTime;

/**
 * Created by SnowFlake on 07.02.2016.
 */
public class AlarmHelper {

    private static AlarmHelper sInstance;
    private Context mContext;
    private AlarmManager mAlarmManager;

    public static AlarmHelper getInstance(){
        if (sInstance == null){
            sInstance = new AlarmHelper();
        }
        return sInstance;
    }

    public void init(Context context){
        this.mContext = context;
        mAlarmManager = (AlarmManager) context.getApplicationContext().
                getSystemService(Context.ALARM_SERVICE);
    }

    public void setAlarm(ReminderTime reminderTime){
        Intent intent = new Intent(mContext, AlarmReceiver.class);
        intent.putExtra("title", reminderTime.getTitle());
        intent.putExtra("description", reminderTime.getDescription());
        intent.putExtra("time_stamp", reminderTime.getDate());

        //intent.putExtra("color", task.getPriorityColor());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext.getApplicationContext(),
                (int) reminderTime.getDate(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        mAlarmManager.set(AlarmManager.RTC_WAKEUP, reminderTime.getDate(), pendingIntent);
    }

    public void removeAlarm(long taskTimeStump){
        Intent intent = new Intent(mContext, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, (int) taskTimeStump,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        mAlarmManager.cancel(pendingIntent);
    }
}
