package com.flys.eplanning.dao.db;

import android.content.Context;

import com.flys.generictools.dao.daoException.DaoException;
import com.flys.generictools.dao.daoImpl.GenericDaoImpl;
import com.flys.generictools.dao.db.DatabaseHelper;
import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.EBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flys.eplanning.R;
import com.flys.eplanning.entities.User;
import com.flys.eplanning.tools.FlysApplicationContext;

/**
 * Created by AMADOU BAKARI on 27/08/2018.
 */
@EBean(scope = EBean.Scope.Singleton)
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao<User, Long> {

    Dao<User, Long> userDao;
    DatabaseHelper<User,Long> databaseHelper;
    Context context;

    @Override
    public Dao<User, Long> getDao() {
        databaseHelper = new DatabaseHelper(FlysApplicationContext.getContext(), R.raw.ormlite_config,getClasses());
        try {
            userDao = (Dao<User, Long>) databaseHelper.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDao;
    }

    @Override
    public void flush() {
        userDao=null;
        if ( databaseHelper.isOpen()){
            databaseHelper.close();
        }
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
    public User findUser(String login, String password) throws DaoException {
        Map<String,Object> params=new HashMap<>();
        List<User> users = new ArrayList<>();
        User result=null;
        params.put("login",login);
        params.put("password",password);
        try {
           users=getDao().queryForFieldValues(params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!users.isEmpty()){
            result=users.get(0);
        }
        return result;
    }
}
