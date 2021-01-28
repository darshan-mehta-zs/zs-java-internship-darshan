package com.zs.hobbytracker.service;

import com.zs.hobbytracker.exception.InvalidInputException;
import com.zs.hobbytracker.models.HobbyAttributes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Interface for all the hobby services
 */
public interface HobbyService {

    /**
     * @param connection      accepts connection to database
     * @param hobbyAttributes accepts hobby to be stored to database
     * @throws InvalidInputException in case exception raises as part of input
     */
    public void tick(Connection connection, HobbyAttributes hobbyAttributes) throws InvalidInputException, SQLException;

    /**
     * Finds longest streak for hobby in number of days
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return An Integer value for longest streak of hobby in number of days
     */
    public int getLongestStreak(Connection connection, int userId) throws SQLException;

    /**
     * Finds latest streak for hobby in number of days
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return An Integer value for longest streak of hobby in number of days
     */
    public int getLatestStreak(Connection connection, int userId) throws SQLException;

    /**
     * Fetch details of when was the last time user performed the hobby
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return HobbyAttributes or its child object containing data
     */
    public HobbyAttributes lastTick(Connection connection, int userId) throws SQLException;

    /**
     * Fetch details of hobby performed by user on given date
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @param date       accepts date
     * @return HobbyAttributes or its child object containing data
     */
    public HobbyAttributes detailsForDate(Connection connection, int userId, Date date) throws SQLException;

}
