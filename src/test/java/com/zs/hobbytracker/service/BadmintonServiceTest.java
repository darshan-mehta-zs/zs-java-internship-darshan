package com.zs.hobbytracker.service;

import com.zs.hobbytracker.exception.ApplicationRuntimeException;
import com.zs.hobbytracker.models.Badminton;
import com.zs.hobbytracker.utils.DatabaseConnection;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * To test Badminton Service
 */
public class BadmintonServiceTest {

    Connection connection;
    BadmintonService badmintonService;

    /**
     * Initialising connection and service
     */
    @Before
    public void setup() {
        connection = DatabaseConnection.getConnection();
        badmintonService = new BadmintonService();
    }

    /**
     * longest streak for badminton hobby when user id is present
     *
     * @throws SQLException
     */
    @Test
    public void getLongestStreakWhenUserIdIsPresent() throws ApplicationRuntimeException {
        int longestStreak = badmintonService.getLongestStreak(connection, 1);
        assertEquals(4, longestStreak);
    }

    /**
     * longest streak for badminton hobby when user id is absent
     *
     * @throws SQLException
     */
    @Test
    public void getLongestStreakWhenUserIdIsAbsent() throws ApplicationRuntimeException {
        int longestStreak = badmintonService.getLongestStreak(connection, 5);
        assertEquals(0, longestStreak);
    }

    /**
     * latest streak for badminton hobby when user id is present
     *
     * @throws SQLException
     */
    @Test
    public void getLatestStreakWhenUserIdIsPresent() throws ApplicationRuntimeException {
        int longestStreak = badmintonService.getLatestStreak(connection, 1);
        assertEquals(2, longestStreak);
    }

    /**
     * longest streak for badminton hobby when user id is absent
     *
     * @throws SQLException
     */
    @Test
    public void getLatestStreakWhenUserIdIsAbsent() throws ApplicationRuntimeException {
        int longestStreak = badmintonService.getLatestStreak(connection, 5);
        assertEquals(0, longestStreak);
    }

    /**
     * Last tick for badminton hobby when user id is present
     */
    @Test
    public void lastTickWhenUserIdIsPresent() throws ApplicationRuntimeException {
        Badminton badminton = badmintonService.lastTick(connection, 1);
        assertEquals(badminton instanceof Badminton, true);
    }

    /**
     * Last tick for badminton hobby when user id is Absent
     */
    @Test
    public void lastTickWhenUserIdIsAbsent() throws ApplicationRuntimeException {
        Badminton badminton = badmintonService.lastTick(connection, 10);
        assertEquals(null, badminton);
    }

    /**
     * Last tick for badminton hobby when user id and date are present in database
     */
    @Test
    public void detailsForDateWhenUserIdAndDateArePresent() throws ApplicationRuntimeException {
        Badminton badminton = badmintonService.detailsForDate(connection, 1, Date.valueOf("2021-01-26"));
        assertEquals(badminton instanceof Badminton, true);
    }

    /**
     * Last tick for badminton hobby when user id or date is Absent in database
     */
    @Test
    public void detailsForDateWhenUserIdOrDateAreAbsent() throws ApplicationRuntimeException {
        Badminton badminton = badmintonService.detailsForDate(connection, 10, Date.valueOf("2021-01-25"));
        assertEquals(null, badminton);
    }


}
