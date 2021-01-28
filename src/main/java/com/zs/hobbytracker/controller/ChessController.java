package com.zs.hobbytracker.controller;

import com.zs.hobbytracker.Hobby;
import com.zs.hobbytracker.exception.InvalidInputException;
import com.zs.hobbytracker.models.Chess;
import com.zs.hobbytracker.service.ChessService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Controller for Chess hobby
 */
public class ChessController {

    private ChessService chessService;
    Scanner scanner;
    Logger logger;

    /**
     * Constructor which injects service, logger and scanner as dependency
     */
    public ChessController() {
        chessService = new ChessService();
        scanner = new Scanner(System.in);
        logger = com.zs.hobbytracker.logger.Logger.getLogger();
    }

    /**
     * Stores chess hobby data in database
     *
     * @param connection accepts connection to database as a parameter
     * @throws InvalidInputException if exception occurs while taking input
     * @throws SQLException
     */
    public void chessTickInput(Connection connection) throws InvalidInputException, SQLException {
        Chess chess = new Chess();
        logger.info("UserId");
        chess.setUserId(scanner.nextInt());
        chess.setHobbyId(2);
        logger.info("Start Time");
        scanner.nextLine();
        String time = scanner.nextLine();
        if (time.charAt(2) != ':' && time.charAt(5) != ':')
            throw new InvalidInputException("Enter time in hh:mm:ss format");
        chess.setStartTime(Time.valueOf(time));
        logger.info("End Time");
        time = scanner.nextLine();
        if (time.charAt(2) != ':' && time.charAt(5) != ':')
            throw new InvalidInputException("Enter time in hh:mm:ss format");
        chess.setEndTime(Time.valueOf(time));
        logger.info("Date Last Played");
        time = scanner.nextLine();
        if (time.charAt(4) != '-' && time.charAt(7) != '-')
            throw new InvalidInputException("Enter dte in yyyy-mm-dd format");
        chess.setDateLastPlayed(Date.valueOf(scanner.nextLine()));
        logger.info("Number Of Moves");
        chess.setNumberOfMoves(scanner.nextInt());
        logger.info("Result");
        scanner.nextLine();
        chess.setResult(scanner.nextLine());
        chess.setTaskCompleted(true);
        chessService.tick(connection, chess);
    }

    /**
     * Finds longest streak for chess hobby in number of days
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return An Integer value for longest streak of chess in number of days
     * @throws SQLException
     */
    public int longestStreak(Connection connection, int userId) throws SQLException {
        int chessLongestStreak = chessService.getLongestStreak(connection, userId);
        return chessLongestStreak;
    }

    /**
     * Finds latest streak for chess hobby in number of days
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return An Integer value for longest streak of chess in number of days
     * @throws SQLException
     */
    public int getLatestStreak(Connection connection, int userId) throws SQLException {
        int chessLatestStreak = chessService.getLatestStreak(connection, userId);
        return chessLatestStreak;
    }

    /**
     * Fetch details of when was the last time user performed the hobby
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return Chess object containing data
     * @throws SQLException
     */
    public Chess lastTick(Connection connection, int userId) throws SQLException {
        if (Hobby.cache.get(userId) != null)
            return (Chess) Hobby.cache.get(userId);
        return chessService.lastTick(connection, userId);
    }

    /**
     * Fetch details of chess hobby performed by user on given date
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @param date       accepts date
     * @return Chess object containing data
     */
    public Chess detailsForDate(Connection connection, int userId, Date date) {
        return chessService.detailsForDate(connection, userId, date);
    }

}
