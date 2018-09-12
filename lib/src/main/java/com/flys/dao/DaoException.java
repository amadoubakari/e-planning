package com.flys.dao;

/**
 * Created by User on 02/05/2018.
 */

public class DaoException extends Exception {
    public DaoException() {
        super();
    }

    public DaoException(String s) {
        super(s);
    }

    public DaoException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DaoException(Throwable throwable) {
        super(throwable);
    }
}
