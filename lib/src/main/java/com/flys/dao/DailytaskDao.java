package com.flys.dao;

import com.flys.entities.DailyTask;
import com.j256.ormlite.dao.Dao;

/**
 * Created by User on 30/04/2018.
 */

public interface DailytaskDao extends Dao<DailyTask, Long> {
    public DailyTask save(DailyTask task) throws DaoException;

    public boolean processBeforeSave(DailyTask task);
}
