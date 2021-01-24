package main.java.com.zs.hobbytracker.controller;

import main.java.com.zs.hobbytracker.models.Chess;
import main.java.com.zs.hobbytracker.service.ChessService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

/**
 * Controller for Chess hobby
 */
public class ChessController {

    private ChessService chessService;
    Scanner scanner;

    /**
     * Constructor which injects service and scanner as dependency
     */
    public ChessController() {
        chessService = new ChessService();
        scanner = new Scanner(System.in);
    }

    /**
     * Stores badminton hobby data in database
     *
     * @param connection accepts connection to database as a parameter
     */
    public void chessTickInput(Connection connection) {
        Chess chess = new Chess();
        System.out.println("UserId");
        chess.setUserId(scanner.nextInt());
        chess.setHobbyId(2);
        System.out.println("Start Time");
        scanner.nextLine();
        chess.setStartTime(Time.valueOf(scanner.nextLine()));
        System.out.println("End Time");
        chess.setEndTime(Time.valueOf(scanner.nextLine()));
        System.out.println("Date Last Played");
        chess.setDateLastPlayed(Date.valueOf(scanner.nextLine()));
        System.out.println("Number Of Moves");
        chess.setNumberOfMoves(scanner.nextInt());
        System.out.println("Result");
        scanner.nextLine();
        chess.setResult(scanner.nextLine());
        chess.setTaskCompleted(true);
        chessService.chessTick(connection, chess);
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
