package com.zs.hobbytracker.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

/**
 * Utility class for establishing connection to database
 */
public class DatabaseConnection {

    /**
     * @return connection established to the database
     */
    public static Connection getConnection() {

        Connection connection = null;
        Logger logger = com.zs.hobbytracker.logger.Logger.getLogger();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:2006/hobby",
                    "darshan", "12345678");
        } catch (Exception e) {
            logger.severe("Database not connected");
            e.printStackTrace();
            System.exit(0);
        }
        return connection;

    }
}
