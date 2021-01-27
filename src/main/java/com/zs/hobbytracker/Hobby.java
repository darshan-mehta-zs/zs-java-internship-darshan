package com.zs.hobbytracker;

import com.zs.hobbytracker.controller.BadmintonController;
import com.zs.hobbytracker.controller.ChessController;
import com.zs.hobbytracker.controller.UserController;
import com.zs.hobbytracker.exception.InvalidInputException;
import com.zs.hobbytracker.lru.Cache;
import com.zs.hobbytracker.lru.LruCache;
import com.zs.hobbytracker.models.Badminton;
import com.zs.hobbytracker.models.Chess;
import com.zs.hobbytracker.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.util.InputMismatchException;
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


    public static final Logger logger = com.zs.hobbytracker.logger.Logger.getLogger();
    public static LruCache lruCache;
    public static Cache cache;

    /**
     * Functionality for hobby
     */
    public static void hobby() {
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            logger.severe("Connection not established");
            logger.info("Not Connected");
        } else {
            Scanner scanner = new Scanner(System.in);
            BadmintonController badmintonController = new BadmintonController();
            ChessController chessController = new ChessController();
            UserController userController = new UserController();

            logger.info("Enter capacity for lru cache");
            int capacity = scanner.nextInt();
            lruCache = new LruCache(capacity);
            cache = new Cache(capacity);

            int choice = 0;
            int userId = 0;
            Badminton badminton;
            Chess chess;
            while (choice != 9) {
                try {

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
                            Object obj = cache.get(userId);
                            if (obj != null) {
                                logger.info("Cached");
                                logger.info(obj + "");
                            } else {
                                badminton = badmintonController.lastTick(connection, userId);
                                if (badminton != null)
                                    logger.info(badminton + "");
                                chess = chessController.lastTick(connection, userId);
                                if (chess != null)
                                    logger.info(chess + "");
                                cache.put(userId, badminton);
                                cache.put(userId, chess);
                            }
//                            if (lruCache.get(userId) != null || cache.get(userId) != null) {
//                                System.out.println("Cached");
//                                System.out.println(lruCache.get(userId));
//                            } else {
//                                badminton = badmintonController.lastTick(connection, userId);
//                                if (badminton != null)
//                                    System.out.println(badminton);
//                                chess = chessController.lastTick(connection, userId);
//                                if (chess != null)
//                                    System.out.println(chess);
//                                lruCache.put(userId, badminton);
//                                lruCache.put(userId, chess);
//                                cache.put(userId, badminton);
//                                cache.put(userId, chess);
//                            }
                            break;
                        case 4:
                            logger.info("Enter User id");
                            userId = scanner.nextInt();
                            scanner.nextLine();
                            String date = "";
                            try {
                                logger.info("Enter date");
                                date = scanner.nextLine();
                            } catch (IllegalArgumentException e) {
                                logger.warning("Enter date in yyyy-mm-dd format");
                                break;
                            }
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
                        case 9:
                            System.exit(0);
                        default:
                            logger.info("DEFAULT");
                            break;
                    }
                } catch (InvalidInputException e) {
                    logger.info(e.getMessage());
                } catch (InputMismatchException e) {
                    logger.severe("Invalid Input");
                } catch (IllegalArgumentException e) {
                    logger.severe("Please provides correct arguments");
                }
            }
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
