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

import com.flys.eplanning.entities.ReminderTime;
import com.flys.eplanning.tools.FlysApplicationContext;

/**
 * Created by User on 14/05/2018.
 */
@EBean(scope = EBean.Scope.Singleton)
public class DailyTaskDaoImpl<DailyTask, Long> extends GenericDaoImpl<DailyTask, Long> implements DailyTaskDao<DailyTask, Long>{

    Dao<DailyTask, Long> dailyTaskLongDao;
    DatabaseHelper<DailyTask,Long> databaseHelper;
    Context context;


    @Override
    public Dao<DailyTask, Long> getDao() {
        databaseHelper = new DatabaseHelper(FlysApplicationContext.getContext(), R.raw.ormlite_config,getClasses());
        try {
            dailyTaskLongDao = (Dao<DailyTask, Long>) databaseHelper.getDao(com.flys.eplanning.entities.DailyTask.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dailyTaskLongDao;
    }

    @Override
    public boolean processBeforeSave(DailyTask dailyTask) {
        return super.processBeforeSave(dailyTask);


    }

    @Override
    public boolean processAfterSave(DailyTask dailyTask) {
        com.flys.eplanning.entities.DailyTask task= (com.flys.eplanning.entities.DailyTask) dailyTask;
        return super.processAfterSave(dailyTask);
    }

    @Override
    public void flush() {
        this.dailyTaskLongDao=null;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    List<Class<?>> getClasses(){
       List <Class<?>> classes=new ArrayList<>();
       classes.add(com.flys.eplanning.entities.DailyTask.class);
       classes.add(ReminderTime.class);
       return classes;
    }
}
