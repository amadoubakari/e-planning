package com.flys.eplanning.dao.db;

import com.flys.eplanning.R;
import com.flys.eplanning.entities.Day;
import com.flys.eplanning.entities.ReminderTime;
import com.flys.eplanning.tools.FlysApplicationContext;
import com.flys.generictools.dao.daoImpl.GenericDaoImpl;
import com.flys.generictools.dao.db.DatabaseHelper;
import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.EBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @param <Day>
 * @param <Long>
 */
@EBean(scope = EBean.Scope.Singleton)
public class DayDaoImpl<Day, Long> extends GenericDaoImpl<Day, Long> implements DayDao<Day, Long> {

    Dao<Day, Long> dayDao;
    DatabaseHelper<Day, Long> databaseHelper;

    @Override
    public Dao<Day, Long> getDao() {
        databaseHelper = new DatabaseHelper(FlysApplicationContext.getContext(), R.raw.ormlite_config, getClasses());
        try {
            dayDao = (Dao<Day, Long>) databaseHelper.getDao(com.flys.eplanning.entities.Day.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dayDao;
    }

    List<Class<?>> getClasses(){
        List <Class<?>> classes=new ArrayList<>();
        classes.add(com.flys.eplanning.entities.DailyTask.class);
        classes.add(com.flys.eplanning.entities.ReminderTime.class);
        classes.add(com.flys.eplanning.entities.User.class);
        classes.add(com.flys.eplanning.entities.Day.class);
        classes.add(com.flys.eplanning.entities.Week.class);
        classes.add(com.flys.eplanning.entities.Month.class);
        return classes;
    }

    @Override
    public void flush() {
        dayDao = null;
        if ( databaseHelper.isOpen()){
            databaseHelper.close();
        }
    }
}
