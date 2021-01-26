package main.java.com.zs.hobbytracker.controller;

import main.java.com.zs.hobbytracker.models.User;
import main.java.com.zs.hobbytracker.service.UserService;

import java.sql.Connection;
import java.util.Scanner;

public class UserController {

    Scanner scanner;
    UserService userService;

    public UserController() {
        scanner = new Scanner(System.in);
        userService = new UserService();
    }

    public void addUser(Connection connection) {
        User user = new User();
        System.out.println("Enter id");
        user.setId(scanner.nextInt());
        System.out.println("Enter name");
        scanner.nextLine();
        user.setName(scanner.nextLine());
        userService.addUser(connection, user);
    }

}
