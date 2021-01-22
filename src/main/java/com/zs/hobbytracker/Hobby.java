package main.java.com.zs.hobbytracker;

import main.java.com.zs.hobbytracker.controller.BadmintonController;
import main.java.com.zs.hobbytracker.controller.ChessController;
import main.java.com.zs.hobbytracker.models.Badminton;
import main.java.com.zs.hobbytracker.models.Chess;
import main.java.com.zs.hobbytracker.utils.DatabaseConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;
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

            int choice = 0;
            int userId = 0;
            Badminton badminton;
            Chess chess;
            while (choice != 9) {
                System.out.println("1.longestStreak for a user : give result hobbywise");
                System.out.println("2.latestStreak for a user : give result hobbywise");
                System.out.println("3.lastTick for a user : give results hobbywise");
                System.out.println("4.details of a given date for a user : give results hobbywise");
                System.out.println("5.Badminton Tick");
                System.out.println("6.Chess Tick");
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
                        badminton = badmintonController.lastTick(connection, userId);
                        System.out.println(badminton);
                        chess = chessController.lastTick(connection, userId);
                        System.out.println(chess);
                        break;
                    case 4:
                        System.out.println("Enter User id");
                        userId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter date");
                        String date = scanner.nextLine();
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
                }
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
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
        Collection collection;
    }

    /**
     * Logger for the class
     *
     * @return logger for logging
     */
    public static Logger getLogger() {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/java/com/zs/hobby/utils/logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger = Logger.getLogger(Hobby.class.getName());
        return logger;
    }

}
