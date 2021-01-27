package com.zs.hobbytracker.service;

import com.zs.hobbytracker.dao.UserDao;
import com.zs.hobbytracker.models.User;

import java.sql.Connection;
import java.util.logging.Logger;

public class UserService {

    UserDao userDao;
    Logger logger;

    public UserService() {
        userDao = new UserDao();
        logger = com.zs.hobbytracker.logger.Logger.getLogger();
    }

    public void addUser(Connection connection, User user) {
        if (userDao.addUser(connection, user) == 1) {
            logger.info("Added");
        } else {
            logger.info("Not added");
        }
    }

}
