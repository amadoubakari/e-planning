package com.flys.dao;

import com.flys.entities.DailyTask;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * @author AMADOU BAKARI
 * @todo
 * @since 30/04/2018
 */

public class DailyTaskDaoImpl extends BaseDaoImpl<DailyTask, Long> implements DailytaskDao {

    protected DailyTaskDaoImpl(ConnectionSource connectionSource, Class<DailyTask> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    @Override
    public DailyTask save(DailyTask task) throws DaoException {
        if (task == null) {
            throw new DaoException("Mot de passe incorrect.");
        }
        if (processBeforeSave(task)) {
            try {
                create(task);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return task;
    }

    @Override
    public boolean processBeforeSave(DailyTask task) {
        boolean result = false;
        if (!task.getNom().matches("")) {
            result = true;
        }
        return result;
    }
}
