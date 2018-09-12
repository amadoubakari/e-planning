package com.flys.eplanning.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.flys.generictools.dao.daoException.DaoException;
import com.flys.generictools.dao.db.DatabaseHelper;
import com.flys.generictools.reminder.NotificationScheduler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flys.eplanning.R;
import com.flys.eplanning.activity.MainActivity_;
import com.flys.eplanning.dao.db.ReminderTimeDaoImpl;
import com.flys.eplanning.entities.DailyTask;
import com.flys.eplanning.entities.ReminderTime;
import com.flys.eplanning.tools.FlysApplicationContext;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle permet de capturer l'événement lié l'alarme et de déclencher l'alarme.
 * @email amadou_bakari@yahoo.fr
 * @tél (+237) 674316936 / 690660199
 * @since 18/05/2018
 */

public class AlarmReceiver extends BroadcastReceiver {
    String TAG = "AlarmReceiver";
    List<ReminderTime> times;
    DatabaseHelper<ReminderTime, Long> databaseHelper;

    protected ReminderTimeDaoImpl<ReminderTime, Long> reminderTimeDao;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        //LocalData localData = new LocalData(context);
        databaseHelper = new DatabaseHelper(FlysApplicationContext.getContext(), R.raw.ormlite_config, getClasses());
        try {
            reminderTimeDao = databaseHelper.getDao(ReminderTime.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            times = reminderTimeDao.getAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");

                for (ReminderTime time : times
                        ) {
                    Log.d(TAG, "Reminder Time :" + time);
                    NotificationScheduler.setReminder(context, AlarmReceiver.class, time.getTitle(), time.getDescription(), time.getHour(), time.getMinute());
                }

                return;
            }
        }

        Log.d(TAG, "onReceive: ");

        //Trigger the notification
        for (ReminderTime time : times
                ) {
            NotificationScheduler.showNotification(context, MainActivity_.class, time.getTitle(), time.getDescription());
        }
    }

    List<Class<?>> getClasses() {
        List<Class<?>> classes = new ArrayList<>();
        classes.add(DailyTask.class);
        classes.add(ReminderTime.class);
        return classes;
    }
}
