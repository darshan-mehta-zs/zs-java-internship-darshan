package com.zs.hobbytracker.service;

import com.zs.hobbytracker.exception.ApplicationRuntimeException;
import com.zs.hobbytracker.models.Badminton;
import com.zs.hobbytracker.models.Chess;
import com.zs.hobbytracker.utils.DatabaseConnection;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Test for chess service
 */
public class ChessServiceTest {

    Connection connection;
    ChessService chessService;

    /**
     * Initialising connection and service
     */
    @Before
    public void setup() {
        connection = DatabaseConnection.getConnection();
        chessService = new ChessService();
    }

    /**
     * longest streak for chess hobby when user id is present
     *
     * @throws SQLException
     */
    @Test
    public void getLongestStreakWhenUserIdIsPresent() throws ApplicationRuntimeException {
        int longestStreak = chessService.getLongestStreak(connection, 1);
        assertEquals(2, longestStreak);
    }

    /**
     * longest streak for chess hobby when user id is absent
     *
     * @throws SQLException
     */
    @Test
    public void getLongestStreakWhenUserIdIsAbsent() throws ApplicationRuntimeException {
        int longestStreak = chessService.getLongestStreak(connection, 5);
        assertEquals(0, longestStreak);
    }

    /**
     * latest streak for chess hobby when user id is present
     *
     * @throws SQLException
     */
    @Test
    public void getLatestStreakWhenUserIdIsPresent() throws ApplicationRuntimeException {
        int longestStreak = chessService.getLatestStreak(connection, 1);
        assertEquals(1, longestStreak);
    }

    /**
     * longest streak for chess hobby when user id is absent
     *
     * @throws SQLException
     */
    @Test
    public void getLatestStreakWhenUserIdIsAbsent() throws ApplicationRuntimeException {
        int longestStreak = chessService.getLatestStreak(connection, 5);
        assertEquals(0, longestStreak);
    }

    /**
     * Last tick for chess hobby when user id is present
     */
    @Test
    public void lastTickWhenUserIdIsPresent() throws ApplicationRuntimeException {
        Chess chess = chessService.lastTick(connection, 1);
        assertEquals(chess instanceof Chess, true);
    }

    /**
     * Last tick for chess hobby when user id is Absent
     */
    @Test
    public void lastTickWhenUserIdIsAbsent() throws ApplicationRuntimeException {
        Chess chess = chessService.lastTick(connection, 10);
        assertEquals(null, chess);
    }

    /**
     * Last tick for badminton hobby when user id and date are present in database
     */
    @Test
    public void detailsForDateWhenUserIdAndDateArePresent() throws ApplicationRuntimeException {
        Chess chess = chessService.detailsForDate(connection, 1, Date.valueOf("2021-01-26"));
        assertEquals(chess instanceof Chess, true);
    }

    /**
     * Last tick for chess hobby when user id or date is Absent in database
     */
    @Test
    public void detailsForDateWhenUserIdOrDateAreAbsent() throws ApplicationRuntimeException {
        Chess chess = chessService.detailsForDate(connection, 10, Date.valueOf("2021-01-25"));
        assertEquals(null, chess);
    }


}
