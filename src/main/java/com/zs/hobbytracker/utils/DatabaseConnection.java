package com.zs.hobbytracker.utils;

import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Utility class for establishing connection to database
 */

public class DatabaseConnection {

    static Connection connection = null;

    /**
     * @return connection established to the database
     */
    public static Connection getConnection() {

        if (connection != null)
            return connection;
        Logger logger = com.zs.hobbytracker.logger.Logger.getLogger();
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:2006/hobby",
                    "darshan", "12345678");
        } catch (SQLException e) {
            logger.severe("Database not connected");
            System.exit(0);
        }
        return connection;
    }

    /**
     * Initialise connection to database
     */
    public static void initialiseConnection() {

        Logger logger = com.zs.hobbytracker.logger.Logger.getLogger();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.severe("Driver Not Found");
            System.exit(0);
        }
    }

}
