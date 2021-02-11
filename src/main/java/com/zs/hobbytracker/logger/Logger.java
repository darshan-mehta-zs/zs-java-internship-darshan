package com.zs.hobbytracker.logger;

import com.zs.hobbytracker.Hobby;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

/**
 * Logger for logging purpose
 */
public class Logger {

    static java.util.logging.Logger logger;

    public static void initialiseLogger() {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resources/loggingHobby.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return logger for the purpose of logging
     */
    public static java.util.logging.Logger getLogger() {
        if (logger != null)
            return logger;
        logger = java.util.logging.Logger.getLogger(Logger.class.getName());
        return logger;
    }
}
