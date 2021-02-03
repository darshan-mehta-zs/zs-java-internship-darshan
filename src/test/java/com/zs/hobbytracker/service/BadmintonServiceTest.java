package com.zs.hobbytracker.service;

import com.zs.hobbytracker.dao.BadmintonDao;
import com.zs.hobbytracker.exception.ApplicationRuntimeException;
import com.zs.hobbytracker.exception.InvalidInputException;
import com.zs.hobbytracker.models.Badminton;
import com.zs.hobbytracker.models.HobbyAttributes;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * Tests the badminton service for functionalities such as longest streak, latest streak , details given the date etc.
 */
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
public class BadmintonServiceTest {

    /**
     * Badminton Service
     */
    private BadmintonService badmintonService;

    /**
     * Mocks the connection to the database
     */
    @Mock
    private Connection connection;

    /**
     * Mocks the badminton dao
     */
    @Mock
    private BadmintonDao badmintonDao;

    /**
     * List for Badminton Model
     */
    private List<HobbyAttributes> hobbies;

    /**
     * Model class object of Badminton
     */
    private HobbyAttributes hobby1;

    /**
     * Model class object of Badminton
     */
    private HobbyAttributes hobby2;

    /**
     * Model class object of Badminton
     */
    private HobbyAttributes hobby3;

    /**
     * Model class object of Badminton
     */
    private HobbyAttributes hobby4;

    /**
     * Model class object of Badminton
     */
    private HobbyAttributes hobby5;

    /**
     * Setup for performing test which initialises the mocks, the models and adds it to list
     */
    @Before
    public void setup() {

        badmintonService = new BadmintonService();
        badmintonService = new BadmintonService(badmintonDao);
        hobby1 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "badminton", 4, "win");
        hobby2 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-04"), "badminton", 4, "WIN");
        hobby3 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-05"), "badminton", 4, "win");
        hobby4 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-07"), "badminton", 4, "win");
        hobby5 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-08"), "badminton", 4, "win");

        hobbies = new ArrayList<>();
        hobbies.add(hobby1);
        hobbies.add(hobby2);
        hobbies.add(hobby3);
        hobbies.add(hobby4);
        hobbies.add(hobby5);

    }

    /**
     * fetches the longest streak for badminton hobby of given user
     *
     * @throws ApplicationRuntimeException
     */
    @Test
    public void getLongestStreakWhenIdIsPresent() throws ApplicationRuntimeException {
        when(badmintonDao.getBadmintonDataUserWise(connection, 1)).thenReturn(hobbies);
        int answer = badmintonService.getLongestStreak(connection, 1);
        assertEquals(3, answer);
    }

    /**
     * Checks for longest streak to be valid if user is absent
     *
     * @throws ApplicationRuntimeException
     */
    @Test
    public void getLongestStreakWhenIdIsAbsent() throws ApplicationRuntimeException {
        when(badmintonDao.getBadmintonDataUserWise(connection, 0)).thenReturn(null);
        int answer = badmintonService.getLongestStreak(connection, 0);
        assertEquals(0, answer);
    }

    /**
     * fetches the latest streak for badminton hobby of given user
     *
     * @throws ApplicationRuntimeException
     */
    @Test
    public void getLatestStreakWhenIdIsPresent() throws ApplicationRuntimeException {
        when(badmintonDao.getBadmintonDataUserWise(connection, 1)).thenReturn(hobbies);
        int answer = badmintonService.getLatestStreak(connection, 1);
        assertEquals(2, answer);
    }

    /**
     * Checks for latest streak to be valid if user is absent
     *
     * @throws ApplicationRuntimeException
     */
    @Test
    public void getLatestStreakWhenIdIsAbsent() throws ApplicationRuntimeException {
        when(badmintonDao.getBadmintonDataUserWise(connection, 0)).thenReturn(null);
        int answer = badmintonService.getLatestStreak(connection, 0);
        assertEquals(0, answer);
    }

    /**
     * Last tick when user id is present
     *
     * @throws ApplicationRuntimeException
     */
    @Test
    public void getLastTickWhenIdIsPresent() throws ApplicationRuntimeException {
        when(badmintonDao.lastTick(connection, 1)).thenReturn((Badminton) hobby1);
        Badminton badminton = badmintonService.lastTick(connection, 1);
        assertEquals(hobby1, badminton);
    }

    /**
     * Last tick when user id is absent
     *
     * @throws ApplicationRuntimeException
     */
    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    @Ignore
    public void getLastTickWhenIdIsAbsent() throws ApplicationRuntimeException {
        when(badmintonDao.getBadmintonDataUserWise(connection, 0)).thenReturn(null);
        Badminton badminton = badmintonService.lastTick(connection, 0);
        assertNull(badminton);
    }


    /**
     * User Details when user id and date are present
     *
     * @throws ApplicationRuntimeException
     */
    @Test
    public void detailsForDateWhenUserIdAndDateArePresent() throws ApplicationRuntimeException {
        when(badmintonDao.detailsForDate(connection, 1, Date.valueOf("2021-01-24"))).thenReturn((Badminton) hobby1);
        Badminton badminton = badmintonService.detailsForDate(connection, 1, Date.valueOf("2021-01-24"));
        assertEquals(hobby1, badminton);
    }

    /**
     * User Details when user id and date are absent
     *
     * @throws ApplicationRuntimeException
     */
    @Test
    public void detailsForDateWhenUserIdAndDateAreAbsent() throws ApplicationRuntimeException {
        when(badmintonDao.detailsForDate(connection, 0, Date.valueOf("2021-01-24"))).thenReturn(null);
        Badminton badminton = badmintonService.detailsForDate(connection, 0, Date.valueOf("2021-01-24"));
        assertNull(badminton);
    }

    /**
     * Test for inserting tick of badminton hobby
     *
     * @throws InvalidInputException
     * @throws ApplicationRuntimeException
     */
    @Test
    public void tickTest() throws InvalidInputException, ApplicationRuntimeException {
        badmintonService.tick(connection, hobby2);
    }

}