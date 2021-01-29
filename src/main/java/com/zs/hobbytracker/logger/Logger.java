package com.zs.hobbytracker.logger;

import com.zs.hobbytracker.Hobby;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

/**
 * Logger for logging purpose
 */
public class Logger {
    /**
     * @return logger for the purpose of logging
     */
    public static java.util.logging.Logger getLogger() {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resources/loggingHobby.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return java.util.logging.Logger.getLogger(Hobby.class.getName());
    }
}
