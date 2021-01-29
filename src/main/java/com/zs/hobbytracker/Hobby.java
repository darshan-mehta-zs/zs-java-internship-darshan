package com.zs.hobbytracker;

import com.zs.hobbytracker.controller.BadmintonController;
import com.zs.hobbytracker.controller.ChessController;
import com.zs.hobbytracker.controller.UserController;
import com.zs.hobbytracker.exception.ApplicationRuntimeException;
import com.zs.hobbytracker.exception.InvalidInputException;
import com.zs.hobbytracker.lru.Cache;
import com.zs.hobbytracker.models.Badminton;
import com.zs.hobbytracker.models.Chess;
import com.zs.hobbytracker.utils.DatabaseConnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;


/**
 * Following class is the entry point for the program of hobbies. User may have multiple hobbies.
 * Users can know their longest streak hobby wise.
 * Users can know their recent streak hobby wise.
 * Users can know when they recently performed hobby.
 * Users can fetch the details hobby wise on any given date
 */
public class Hobby {


    /**
     * Logger for logging
     */
    public static final Logger logger = com.zs.hobbytracker.logger.Logger.getLogger();

    /**
     * LRU cache implementation
     */
    public static Cache cache;

    /**
     * Badminton Controller to perform operation on badminton hobby
     */
    static BadmintonController badmintonController;

    /**
     * Chess Controller to perform operation on badminton hobby
     */
    static ChessController chessController;

    /**
     * User Controller to perform operation on badminton hobby
     */
    static UserController userController;

    /**
     * To take user input
     */
    static Scanner scanner;

    /**
     * Initialises the controllers and scanner
     */
    public static void initialise() throws ApplicationRuntimeException {
        badmintonController = new BadmintonController();
        chessController = new ChessController();
        userController = new UserController();
        scanner = new Scanner(System.in);
        FileReader reader = null;
        try {
            reader = new FileReader("src/main/resources/application.properties");
            Properties p = new Properties();
            p.load(reader);
            cache = new Cache(Integer.parseInt(p.getProperty("cache")));
        } catch (IOException e) {
            throw new ApplicationRuntimeException(500, "Cannot load properties file");
        }

    }

    /**
     * Functionality for hobby
     */
    public static void hobby() {

        try {
            Connection connection = DatabaseConnection.getConnection();
            if (connection == null)
                throw new ApplicationRuntimeException(500, "Database Connection not established");
            initialise();
            int choice = 0;
            int userId;
            Badminton badminton;
            Chess chess;
            while (choice != 9) {

                logger.info("1.longestStreak for a user : give result hobby wise");
                logger.info("2.latestStreak for a user : give result hobby wise");
                logger.info("3.lastTick for a user : give results hobby wise");
                logger.info("4.details of a given date for a user : give results hobby wise");
                logger.info("5.Badminton Tick");
                logger.info("6.Chess Tick");
                logger.info("7.Create User");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        logger.info("Enter User id");
                        userId = scanner.nextInt();
                        int longestStreakBadminton = badmintonController.getLongestStreak(connection, userId);
                        logger.info("Badminton " + longestStreakBadminton);
                        int longestStreakChess = chessController.longestStreak(connection, userId);
                        logger.info("Chess " + longestStreakChess);
                        break;
                    case 2:
                        logger.info("Enter User id");
                        userId = scanner.nextInt();
                        int latestStreakBadminton = badmintonController.getLatestStreak(connection, userId);
                        logger.info("Badminton " + latestStreakBadminton);
                        int latestStreakChess = chessController.getLatestStreak(connection, userId);
                        logger.info("Chess " + latestStreakChess);
                        break;
                    case 3:
                        logger.info("Enter User id");
                        userId = scanner.nextInt();
                        badminton = badmintonController.lastTick(connection, userId);
                        logger.info(badminton + "");
                        chess = chessController.lastTick(connection, userId);
                        logger.info(chess + "");
                        cache.put(userId, badminton);
                        cache.put(userId, chess);
                        break;
                    case 4:
                        logger.info("Enter User id");
                        userId = scanner.nextInt();
                        scanner.nextLine();
                        String date = "";
                        logger.info("Enter date");
                        date = scanner.nextLine();
                        badminton = badmintonController.detailsForDate(connection, userId, Date.valueOf(date));
                        logger.info(badminton + "");
                        chess = chessController.detailsForDate(connection, userId, Date.valueOf(date));
                        logger.info(chess + "");
                        break;
                    case 5:
                        badmintonController.badmintonTickInput(connection);
                        break;
                    case 6:
                        chessController.chessTickInput(connection);
                        break;
                    case 7:
                        userController.addUser(connection);
                        break;
                    default:
                        logger.info("DEFAULT");
                        break;
                }
            }

        } catch (InvalidInputException e) {
            logger.severe("\n{\nerrorCode=" + e.getErrorCode() + ",\n" + "message=" + e.getErrorMessage() + "\n}");
        } catch (ApplicationRuntimeException e) {
            logger.severe("\n{\nerrorCode=" + e.getErrorCode() + ",\n" + "message=" + e.getErrorMessage() + "\n}");
        }
    }

    /**
     * Entry point for the program
     *
     * @param args parameters as an array
     */
    public static void main(String[] args) {
        hobby();
    }


}
