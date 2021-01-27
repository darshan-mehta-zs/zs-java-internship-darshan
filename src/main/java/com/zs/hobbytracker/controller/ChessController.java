package com.zs.hobbytracker.controller;

import com.zs.hobbytracker.Hobby;
import com.zs.hobbytracker.exception.InvalidInputException;
import com.zs.hobbytracker.models.Chess;
import com.zs.hobbytracker.service.ChessService;

import java.sql.Connection;
import java.sql.Date;
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
     * Constructor which injects service and scanner as dependency
     */
    public ChessController() {
        chessService = new ChessService();
        scanner = new Scanner(System.in);
        logger = com.zs.hobbytracker.logger.Logger.getLogger();
    }

    /**
     * Stores badminton hobby data in database
     *
     * @param connection accepts connection to database as a parameter
     * @throws InvalidInputException if exception occurs while taking input
     */
    public void chessTickInput(Connection connection) throws InvalidInputException {
        Chess chess = new Chess();
        logger.info("UserId");
        chess.setUserId(scanner.nextInt());
        chess.setHobbyId(2);
        try {
            logger.info("Start Time");
            scanner.nextLine();
            chess.setStartTime(Time.valueOf(scanner.nextLine()));
            logger.info("End Time");
            chess.setEndTime(Time.valueOf(scanner.nextLine()));
        } catch (IllegalArgumentException e) {
            Hobby.logger.warning("Enter time in 24 hr format hh:mm:ss");
            return;
        }
        logger.info("Date Last Played");
        try {
            chess.setDateLastPlayed(Date.valueOf(scanner.nextLine()));
        } catch (IllegalArgumentException e) {
            Hobby.logger.warning("Enter date in yyyy-mm-dd format");
            return;
        }
        logger.info("Number Of Moves");
        chess.setNumberOfMoves(scanner.nextInt());
        logger.info("Result");
        scanner.nextLine();
        chess.setResult(scanner.nextLine());
        chess.setTaskCompleted(true);
//        InputValidator.validate(chess);
        chessService.tick(connection, chess);
    }

    /**
     * Finds longest streak for chess hobby in number of days
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return An Integer value for longest streak of chess in number of days
     */
    public int longestStreak(Connection connection, int userId) {
        int chessLongestStreak = chessService.getLongestStreak(connection, userId);
        return chessLongestStreak;
    }

    /**
     * Finds latest streak for chess hobby in number of days
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return An Integer value for longest streak of chess in number of days
     */
    public int getLatestStreak(Connection connection, int userId) {
        int chessLatestStreak = chessService.getLatestStreak(connection, userId);
        return chessLatestStreak;
    }

    /**
     * Fetch details of when was the last time user performed the hobby
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return Chess object containing data
     */
    public Chess lastTick(Connection connection, int userId) {
        return chessService.lastTick(connection, userId);
    }

    /**
     * Fetch details of badminton hobby performed by user on given date
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @param date       accepts date
     * @return Badminton object containing data
     */
    public Chess detailsForDate(Connection connection, int userId, Date date) {
        return chessService.detailsForDate(connection, userId, date);
    }

}
