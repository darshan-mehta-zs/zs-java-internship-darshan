package com.zs.hobbytracker.dao;

import com.zs.hobbytracker.exception.ApplicationRuntimeException;
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
     * @return if user is added to the database
     * @throws ApplicationRuntimeException
     */
    public int addUser(Connection connection, User user) throws ApplicationRuntimeException {
        String query = "insert into users(user_id,name) values(?,?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationRuntimeException("User Not Added");
        }
    }
}
