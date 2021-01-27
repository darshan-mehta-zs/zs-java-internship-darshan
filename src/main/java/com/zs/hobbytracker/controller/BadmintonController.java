package com.zs.hobbytracker.controller;

import com.zs.hobbytracker.Hobby;
import com.zs.hobbytracker.exception.InvalidInputException;
import com.zs.hobbytracker.models.Badminton;
import com.zs.hobbytracker.service.BadmintonService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Controller for Badminton hobby
 */
public class BadmintonController {

    private BadmintonService badmintonService;
    private Scanner scanner;
    private Logger logger;

    /**
     * Constructor which injects service and scanner as dependency
     */
    public BadmintonController() {
        badmintonService = new BadmintonService();
        scanner = new Scanner(System.in);
        logger = com.zs.hobbytracker.logger.Logger.getLogger();
    }

    /**
     * Stores badminton hobby data in database
     *
     * @param connection accepts connection to database as a parameter
     * @throws InvalidInputException if exception occurs while taking input
     */
    public void badmintonTickInput(Connection connection) throws InvalidInputException {
        Badminton badminton = new Badminton();
        logger.info("UserId");
        badminton.setUserId(scanner.nextInt());
        badminton.setHobbyId(1);
        try {
            logger.info("Start Time");
            scanner.nextLine();
            badminton.setStartTime(Time.valueOf(scanner.nextLine()));
            logger.info("End Time");
            badminton.setEndTime(Time.valueOf(scanner.nextLine()));
        } catch (IllegalArgumentException e) {
            Hobby.logger.warning("Enter time in 24 hr format hh:mm:ss");
            return;
        }
        logger.info("Date Last Played");
        try {
            badminton.setDateLastPlayed(Date.valueOf(scanner.nextLine()));
        } catch (IllegalArgumentException e) {
            Hobby.logger.warning("Enter date in yyyy-mm-dd format");
            return;
        }
        logger.info("Number Of Players");
        badminton.setNumberOfPlayers(scanner.nextInt());
        logger.info("Result");
        scanner.nextLine();
        badminton.setResult(scanner.nextLine());
        badminton.setTaskCompleted(true);
        badmintonService.tick(connection, badminton);
    }

    /**
     * Finds longest streak for badminton hobby in number of days
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return An Integer value for longest streak of badminton in number of days
     */
    public int getLongestStreak(Connection connection, int userId) {
        int badmintonLongestStreak = badmintonService.getLongestStreak(connection, userId);
        return badmintonLongestStreak;
    }

    /**
     * Finds latest streak for badminton hobby in number of days
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return An Integer value for longest streak of badminton in number of days
     */
    public int getLatestStreak(Connection connection, int userId) {
        int badmintonLatestStreak = badmintonService.getLatestStreak(connection, userId);
        return badmintonLatestStreak;
    }

    /**
     * Fetch details of when was the last time user performed the hobby
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return Badminton object containing data
     */
    public Badminton lastTick(Connection connection, int userId) {
        return badmintonService.lastTick(connection, userId);
    }

    /**
     * Fetch details of badminton hobby performed by user on given date
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @param date       accepts date
     * @return Badminton object containing data
     */
    public Badminton detailsForDate(Connection connection, int userId, Date date) {
        return badmintonService.detailsForDate(connection, userId, date);
    }

}
