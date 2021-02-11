package com.zs.hobbytracker.service;

import com.zs.hobbytracker.dao.ChessDao;
import com.zs.hobbytracker.exception.ApplicationRuntimeException;
import com.zs.hobbytracker.exception.InvalidInputException;
import com.zs.hobbytracker.models.Chess;
import com.zs.hobbytracker.models.HobbyAttributes;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChessServiceTest {
    /**
     * Chess Service
     */
    private ChessService chessService;

    /**
     * Mocks the connection to the database
     */
    @Mock
    private Connection connection;

    /**
     * Mocks the Chess dao
     */
    @Mock
    private ChessDao chessDao;

    private List<HobbyAttributes> hobbies;

    private HobbyAttributes hobby1;
    private HobbyAttributes hobby2;
    private HobbyAttributes hobby3;
    private HobbyAttributes hobby4;
    private HobbyAttributes hobby5;

    /**
     * Setup
     */
    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        chessService = new ChessService();
        chessService = new ChessService(chessDao);
        hobby1 = new Chess(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "chess", 4, "win");
        hobby2 = new Chess(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-04"), "chess", 4, "win");
        hobby3 = new Chess(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-05"), "chess", 4, "win");
        hobby4 = new Chess(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-07"), "chess", 4, "win");
        hobby5 = new Chess(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-08"), "chess", 4, "win");

        hobbies = new ArrayList<>();
        hobbies.add(hobby1);
        hobbies.add(hobby2);
        hobbies.add(hobby3);
        hobbies.add(hobby4);
        hobbies.add(hobby5);

    }

    /**
     * fetches the longest streak for Chess hobby of given user
     *
     * @throws ApplicationRuntimeException
     */
    @Test
    public void getLongestStreakWhenIdIsPresent() throws ApplicationRuntimeException {
        when(chessDao.getChessDataUserWise(connection, 1)).thenReturn(hobbies);
        int answer = chessService.getLongestStreak(connection, 1);
        assertEquals(3, answer);
    }

    /**
     * Checks for longest streak to be valid if user is absent
     *
     * @throws ApplicationRuntimeException
     */
    @Test
    public void getLongestStreakWhenIdIsAbsent() throws ApplicationRuntimeException {
        when(chessDao.getChessDataUserWise(connection, 0)).thenReturn(null);
        int answer = chessService.getLongestStreak(connection, 0);
        assertEquals(0, answer);
    }

    /**
     * fetches the latest streak for Chess hobby of given user
     *
     * @throws ApplicationRuntimeException
     */
    @Test
    public void getLatestStreakWhenIdIsPresent() throws ApplicationRuntimeException {
        when(chessDao.getChessDataUserWise(connection, 1)).thenReturn(hobbies);
        int answer = chessService.getLatestStreak(connection, 1);
        assertEquals(2, answer);
    }

    /**
     * Checks for latest streak to be valid if user is absent
     *
     * @throws ApplicationRuntimeException
     */
    @Test
    public void getLatestStreakWhenIdIsAbsent() throws ApplicationRuntimeException {
        when(chessDao.getChessDataUserWise(connection, 0)).thenReturn(null);
        int answer = chessService.getLatestStreak(connection, 0);
        assertEquals(0, answer);
    }

    /**
     * Last tick when user id is present
     *
     * @throws ApplicationRuntimeException
     */
    @Test
    public void getLastTickWhenIdIsPresent() throws ApplicationRuntimeException {
        when(chessDao.lastTick(connection, 1)).thenReturn((Chess) hobby1);
        Chess chess = chessService.lastTick(connection, 1);
        assertEquals(hobby1, chess);
    }

    /**
     * Last tick when user id is absent
     *
     * @throws ApplicationRuntimeException
     */
    @Test
    @Ignore
    public void getLastTickWhenIdIsAbsent() throws ApplicationRuntimeException {
        when(chessDao.getChessDataUserWise(connection, 0)).thenReturn(null);
        Chess chess = chessService.lastTick(connection, 0);
        assertNull(chess);
    }


    /**
     * User Details when user id and date are present
     *
     * @throws ApplicationRuntimeException
     */
    @Test
    public void detailsForDateWhenUserIdAndDateArePresent() throws ApplicationRuntimeException {
        when(chessDao.detailsForDate(connection, 1, Date.valueOf("2021-01-24"))).thenReturn((Chess) hobby1);
        Chess chess = chessService.detailsForDate(connection, 1, Date.valueOf("2021-01-24"));
        assertEquals(hobby1, chess);
    }

    /**
     * User Details when user id and date are absent
     *
     * @throws ApplicationRuntimeException
     */
    @Test
    public void detailsForDateWhenUserIdAndDateAreAbsent() throws ApplicationRuntimeException {
        when(chessDao.detailsForDate(connection, 0, Date.valueOf("2021-01-24"))).thenReturn(null);
        Chess chess = chessService.detailsForDate(connection, 0, Date.valueOf("2021-01-24"));
        assertNull(chess);
    }

    /**
     * Test for inserting tick of chess hobby
     *
     * @throws InvalidInputException
     * @throws ApplicationRuntimeException
     */
    @Test
    public void tickTest() throws InvalidInputException, ApplicationRuntimeException {
        chessService.tick(connection, hobby2);
    }

}
