package com.flys.eplanning.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.flys.generictools.dao.daoException.DaoException;
import com.flys.generictools.dao.db.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flys.eplanning.R;
import com.flys.eplanning.dao.db.ReminderTimeDaoImpl;
import com.flys.eplanning.entities.DailyTask;
import com.flys.eplanning.entities.ReminderTime;
import com.flys.eplanning.tools.FlysApplicationContext;

/**
 * Created by SnowFlake on 07.02.2016.
 */
public class AlarmSetter extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        List<ReminderTime> times;
        DatabaseHelper<ReminderTime, Long> databaseHelper;

        AlarmHelper.getInstance().init(context);
        AlarmHelper alarmHelper = AlarmHelper.getInstance();

        ReminderTimeDaoImpl<ReminderTime, Long> reminderTimeDao = null;
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
        // DBHelper dbHelper = new DBHelper(context);

        List<ReminderTime> tasks = new ArrayList<>();



        for (ReminderTime task : tasks) {
            if (task.getDate() != 0) {
                Log.e(getClass().getSimpleName(),"++++++++++++++++++++++++ Task :"+task);
                alarmHelper.setAlarm(task);
            }
        }
    }

    List<Class<?>> getClasses() {
        List<Class<?>> classes = new ArrayList<>();
        classes.add(DailyTask.class);
        classes.add(ReminderTime.class);
        return classes;
    }
}
