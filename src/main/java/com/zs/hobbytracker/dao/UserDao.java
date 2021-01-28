package com.zs.hobbytracker.dao;

import com.zs.hobbytracker.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * User dao to read or write from or to the database
 */
public class UserDao {

    PreparedStatement statement;

    /**
     * @param connection accepts connection to the database
     * @param user       accepts user object to add user to database
     * @return 1 if user is added else 0
     */
    public int addUser(Connection connection, User user) {
        String query = "insert into users(user_id,name) values(?,?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
