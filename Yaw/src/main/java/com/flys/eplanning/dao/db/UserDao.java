package com.flys.eplanning.dao.db;

import com.flys.generictools.dao.IDao.GenericDao;
import com.flys.generictools.dao.daoException.DaoException;

/**
 * Created by AMADOU BAKARI on 27/08/2018.
 */

public interface UserDao<User, Long> extends GenericDao<User, Long> {

    /**
     * Elle permet de rechercher un utilisateur fonction de son login et de son mot de passe.
     *
     * @param login
     * @param password
     * @return
     * @throws DaoException
     */
    public User findUser(String login, String password) throws DaoException;
}
