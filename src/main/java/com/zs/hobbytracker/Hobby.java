package main.java.com.zs.hobbytracker;

import main.java.com.zs.hobbytracker.controller.BadmintonController;
import main.java.com.zs.hobbytracker.controller.ChessController;
import main.java.com.zs.hobbytracker.controller.UserController;
import main.java.com.zs.hobbytracker.exception.InvalidInputException;
import main.java.com.zs.hobbytracker.lru.LruCache;
import main.java.com.zs.hobbytracker.models.Badminton;
import main.java.com.zs.hobbytracker.models.Chess;
import main.java.com.zs.hobbytracker.models.HobbyAttributes;
import main.java.com.zs.hobbytracker.utils.DatabaseConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;


/**
 * Following class is the entry point for the program of hobbies. User may have multiple hobbies.
 * Users can know their longest streak hobby wise.
 * Users can know their recent streak hobby wise.
 * Users can know when they recently performed hobby.
 * Users can fetch the details hobby wise on any given date
 */
public class Hobby {

    public static Logger logger;
    public static LruCache lruCache;

    /**
     * Functionality for hobby
     */
    public static void hobby() {
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            logger.severe("Connection not established");
            System.out.println("Not Connected");
        } else {
            Scanner scanner = new Scanner(System.in);
            BadmintonController badmintonController = new BadmintonController();
            ChessController chessController = new ChessController();
            UserController userController = new UserController();

            System.out.println("Enter capacity for lru cache");
            int capacity = scanner.nextInt();
            lruCache = new LruCache(capacity);

            int choice = 0;
            int userId = 0;
            Badminton badminton;
            Chess chess;
            String key = "";
            int value = 0;
            while (choice != 9) {
                try {

                    System.out.println("1.longestStreak for a user : give result hobby wise");
                    System.out.println("2.latestStreak for a user : give result hobby wise");
                    System.out.println("3.lastTick for a user : give results hobby wise");
                    System.out.println("4.details of a given date for a user : give results hobby wise");
                    System.out.println("5.Badminton Tick");
                    System.out.println("6.Chess Tick");
                    System.out.println("7.Create User");
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Enter User id");
                            userId = scanner.nextInt();
                            int longestStreakBadminton = badmintonController.getLongestStreak(connection, userId);
                            System.out.println("Badminton " + longestStreakBadminton);
                            int longestStreakChess = chessController.longestStreak(connection, userId);
                            System.out.println("Chess " + longestStreakChess);
                            break;
                        case 2:
                            System.out.println("Enter User id");
                            userId = scanner.nextInt();
                            int latestStreakBadminton = badmintonController.getLatestStreak(connection, userId);
                            System.out.println("Badminton " + latestStreakBadminton);
                            int latestStreakChess = chessController.getLatestStreak(connection, userId);
                            System.out.println("Chess " + latestStreakChess);
                            break;
                        case 3:
                            System.out.println("Enter User id");
                            userId = scanner.nextInt();
                            if (lruCache.get(userId) != null) {
                                System.out.println("Cached");
                                System.out.println(lruCache.get(userId));
                            } else {
                                badminton = badmintonController.lastTick(connection, userId);
                                if (badminton != null)
                                    System.out.println(badminton);
                                chess = chessController.lastTick(connection, userId);
                                if (chess != null)
                                    System.out.println(chess);
                                lruCache.put(userId, badminton);
                                lruCache.put(userId, chess);
                            }
                            break;
                        case 4:
                            System.out.println("Enter User id");
                            userId = scanner.nextInt();
                            scanner.nextLine();
                            String date = "";
                            try {
                                System.out.println("Enter date");
                                date = scanner.nextLine();
                            } catch (IllegalArgumentException e) {
                                logger.warning("Enter date in yyyy-mm-dd format");
                                break;
                            }
                            badminton = badmintonController.detailsForDate(connection, userId, Date.valueOf(date));
                            System.out.println(badminton);
                            chess = chessController.detailsForDate(connection, userId, Date.valueOf(date));
                            System.out.println(chess);
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
                        case 9:
                            System.exit(0);
                        default:
                            System.out.println("DEFAULT");
                            break;
                    }
                } catch (InvalidInputException e) {
                    logger.info(e.getMessage());
                } catch (InputMismatchException e) {
                    logger.severe("Invalid Input");
                    System.out.println("Invalid Input");
                } catch (IllegalArgumentException e) {
                    logger.severe("Please provides correct arguments");
                }
            }


        }
    }


    /**
     * Entry point for the program
     *
     * @param args
     */
    public static void main(String[] args) {
        logger = getLogger();
        hobby();
    }

    /**
     * Logger for the class
     *
     * @return logger for logging
     */
    public static Logger getLogger() {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/java/com/zs/hobbytracker/utils/logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger = Logger.getLogger(Hobby.class.getName());
        return logger;
    }

}
