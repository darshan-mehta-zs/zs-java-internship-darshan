package main.java.com.zs.hobbytracker.service;

import main.java.com.zs.hobbytracker.dao.UserDao;
import main.java.com.zs.hobbytracker.models.User;

import java.sql.Connection;

public class UserService {

    UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public void addUser(Connection connection, User user) {
        if (userDao.addUser(connection, user) == 1) {
            System.out.println("Added");
        } else {
            System.out.println("Not added");
        }
    }

}
