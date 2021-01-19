package main.java.com.zs.ecom.Models;


import main.java.com.zs.ecom.utils.DbConnection;

import java.sql.*;
import java.util.Scanner;

public class Users {

    String mobileNumber;
    String username;
    String password;
    String emailId;

    public static Connection connection = DbConnection.getConnection();

    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter mobile number");
        mobileNumber = scanner.nextLine();
        System.out.println("Enter user name");
        username = scanner.nextLine();
        System.out.println("Enter password");
        password = scanner.nextLine();
        System.out.println("Enter email id");
        emailId = scanner.nextLine();
    }

    public void addUser() throws SQLException {
        input();
        PreparedStatement st = connection.prepareStatement("INSERT INTO users (mobile_number, password, username, email_id) VALUES (?, ?, ?, ?)");
        st.setString(1, mobileNumber);
        st.setString(2, password);
        st.setString(3, username);
        st.setObject(4, emailId);
        st.executeUpdate();
        st.close();
    }

    public void updateUser() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter mobile Number");
        String mobile = scanner.nextLine();
        String query = "select * from users where mobile_number=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, mobile);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) {
            System.out.println("Does not exist");
            return;
        }
        System.out.println("Enter user name");
        username = scanner.nextLine();
        System.out.println("Enter password");
        password = scanner.nextLine();
        System.out.println("Enter email id");
        emailId = scanner.nextLine();
        System.out.println(username + " " + password + " " + emailId);
        statement = connection.prepareStatement("update users set username=?, password=?, email_id=? where mobile_number=?");
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, emailId);
        statement.setString(4, mobile);
        statement.executeUpdate();

    }

    public void readUser() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");
        while (resultSet.next()) {
            mobileNumber = resultSet.getString("mobile_number");
            username = resultSet.getString("username");
            password = resultSet.getString("password");
            emailId = resultSet.getString("email_id");
            System.out.println(mobileNumber + " " + username + " " + password + " " + emailId);
        }
    }

    public void readUserByMobileNumber() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter mobile Number");
        String mobile = scanner.nextLine();
        String query = "select * from users where mobile_number=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, mobile);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            mobileNumber = resultSet.getString("mobile_number");
            username = resultSet.getString("username");
            password = resultSet.getString("password");
            emailId = resultSet.getString("email_id");
            System.out.println(mobileNumber + " " + username + " " + password + " " + emailId);
        }

    }

    public void deleteUser() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter mobile Number");
        String mobile = scanner.nextLine();
        if (!userExists(mobile)) {
            System.out.println("Does not exist");
            return;
        }
        String query = "delete from users where mobile_number=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, mobile);
        statement.executeUpdate();
    }

    public boolean userExists(String mobileNumber) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String query = "select * from users where mobile_number=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, mobileNumber);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) {
            return false;
        }
        return true;
    }

}
