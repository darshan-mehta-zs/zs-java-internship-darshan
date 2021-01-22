package main.java.com.zs.hobbytracker.utils;

import main.java.com.zs.hobbytracker.Hobby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class DatabaseConnection {
    public static Connection getConnection() {
        Connection connection = null;
        Logger logger = Hobby.getLogger();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:2006/hobby",
                    "darshan", "12345678");
        } catch (Exception e) {
            logger.severe("Database not connected");
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return connection;
    }
}
