package com.flys.eplanning.dao.db;

import android.content.Context;

import com.flys.generictools.dao.daoImpl.GenericDaoImpl;
import com.flys.generictools.dao.db.DatabaseHelper;
import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.EBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.flys.eplanning.R;

import com.flys.eplanning.tools.FlysApplicationContext;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle implémente toutes les opérations définies sur l'interface ReminderTimeDao.
 * @email amadou_bakari@yahoo.fr
 * @tel (+237) 674316936/690660199
 * @since 23/05/2018
 */

@EBean(scope = EBean.Scope.Singleton)
public class ReminderTimeDaoImpl<ReminderTime, Long> extends GenericDaoImpl<ReminderTime, Long> implements ReminderTimeDao <ReminderTime, Long>  {

    Dao<ReminderTime, Long> reminderTimeLongDao;
    DatabaseHelper<ReminderTime,Long> databaseHelper;
    Context context;

    @Override
    public Dao<ReminderTime, Long> getDao() {
        databaseHelper = new DatabaseHelper(FlysApplicationContext.getContext(), R.raw.ormlite_config,getClasses());
        try {
            reminderTimeLongDao = (Dao<ReminderTime, Long>) databaseHelper.getDao(com.flys.eplanning.entities.ReminderTime.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reminderTimeLongDao;
    }

    @Override
    public void flush() {
        reminderTimeLongDao=null;
    }
    List<Class<?>> getClasses(){
        List <Class<?>> classes=new ArrayList<>();
        classes.add(com.flys.eplanning.entities.DailyTask.class);
        classes.add(com.flys.eplanning.entities.ReminderTime.class);
        return classes;
    }
}
