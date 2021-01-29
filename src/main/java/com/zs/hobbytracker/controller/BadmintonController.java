package com.zs.hobbytracker.controller;

import com.zs.hobbytracker.Hobby;
import com.zs.hobbytracker.exception.ApplicationRuntimeException;
import com.zs.hobbytracker.exception.InvalidInputException;
import com.zs.hobbytracker.models.Badminton;
import com.zs.hobbytracker.models.HobbyAttributes;
import com.zs.hobbytracker.service.BadmintonService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
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
     * Constructor which injects service, logger and  scanner as dependency
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
     * @throws InvalidInputException       if exception occurs while taking input
     * @throws ApplicationRuntimeException
     */
    public void badmintonTickInput(Connection connection) throws InvalidInputException, ApplicationRuntimeException {
        Badminton badminton = new Badminton();
        logger.info("UserId");
        badminton.setUserId(scanner.nextInt());
        badminton.setHobbyId(1);
        logger.info("Start Time");
        scanner.nextLine();
        String time = scanner.nextLine();
        if (time.charAt(2) != ':' && time.charAt(5) != ':')
            throw new InvalidInputException("Enter time in hh:mm:ss format");
        badminton.setStartTime(Time.valueOf(time));
        logger.info("End Time");
        time = scanner.nextLine();
        if (time.charAt(2) != ':' && time.charAt(5) != ':')
            throw new InvalidInputException("Enter time in hh:mm:ss format");
        badminton.setEndTime(Time.valueOf(time));
        logger.info("Date Last Played");
        time = scanner.nextLine();
        if (time.charAt(4) != '-' && time.charAt(7) != '-')
            throw new InvalidInputException("Enter dte in yyyy-mm-dd format");
        badminton.setDateLastPlayed(Date.valueOf(time));
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
     * @throws ApplicationRuntimeException
     */
    public int getLongestStreak(Connection connection, int userId) throws ApplicationRuntimeException {
        return badmintonService.getLongestStreak(connection, userId);
    }

    /**
     * Finds latest streak for badminton hobby in number of days
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return An Integer value for longest streak of badminton in number of days
     * @throws ApplicationRuntimeException
     */
    public int getLatestStreak(Connection connection, int userId) throws ApplicationRuntimeException {
        return badmintonService.getLatestStreak(connection, userId);
    }

    /**
     * Fetch details of when was the last time user performed the hobby
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return Badminton object containing data
     * @throws ApplicationRuntimeException
     */
    public Badminton lastTick(Connection connection, int userId) throws ApplicationRuntimeException {
        if (Hobby.cache.get(userId) != null)
            return (Badminton) Hobby.cache.get(userId);
        return badmintonService.lastTick(connection, userId);
    }

    /**
     * Fetch details of badminton hobby performed by user on given date
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @param date       accepts date
     * @return Badminton object containing data
     * @throws ApplicationRuntimeException
     */
    public Badminton detailsForDate(Connection connection, int userId, Date date) throws ApplicationRuntimeException {
        return badmintonService.detailsForDate(connection, userId, date);
    }

}
