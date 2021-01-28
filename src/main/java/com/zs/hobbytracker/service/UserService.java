package com.zs.hobbytracker.service;

import com.zs.hobbytracker.dao.UserDao;
import com.zs.hobbytracker.models.User;

import java.sql.Connection;
import java.util.logging.Logger;

/**
 * Service class for User
 */
public class UserService {

    UserDao userDao;
    Logger logger;

    /**
     * Constructor which injects user dao and logger as dependency
     */
    public UserService() {
        userDao = new UserDao();
        logger = com.zs.hobbytracker.logger.Logger.getLogger();
    }

    /**
     * Adds the user data to the database
     *
     * @param connection accepts connection to the database
     * @param user       accepts user data to be stored
     */
    public void addUser(Connection connection, User user) {
        if (userDao.addUser(connection, user) == 1) {
            logger.info("Added");
        } else {
            logger.info("Not added");
        }
    }

}
