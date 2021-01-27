package com.zs.hobbytracker.controller;

import com.zs.hobbytracker.models.User;
import com.zs.hobbytracker.service.UserService;

import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;

public class UserController {

    Scanner scanner;
    UserService userService;
    Logger logger;

    /**
     * Constructor for initialising service and scanner
     */
    public UserController() {
        scanner = new Scanner(System.in);
        userService = new UserService();
        logger = com.zs.hobbytracker.logger.Logger.getLogger();
    }

    /**
     * To add user to the database
     *
     * @param connection accepts connection as a parameter
     */
    public void addUser(Connection connection) {
        User user = new User();
        logger.info("Enter id");
        user.setId(scanner.nextInt());
        logger.info("Enter name");
        scanner.nextLine();
        user.setName(scanner.nextLine());
        userService.addUser(connection, user);
    }

}
