package com.zs.hobbytracker.dao;

import com.zs.hobbytracker.Hobby;
import com.zs.hobbytracker.exception.ApplicationRuntimeException;
import com.zs.hobbytracker.models.Badminton;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Test class for badminton DAO
 */
@RunWith(MockitoJUnitRunner.class)
public class BadmintonDaoTestMockito {

    /**
     * Badminton DAO
     */
    BadmintonDao badmintonDao;

    /**
     * TO mock the Connection object
     */
    @Mock
    Connection connection;

    /**
     * To mock the prepared statement
     */
    @Mock
    PreparedStatement statement;

    /**
     * TO mock the result set
     */
    @Mock
    ResultSet resultSet;

    /**
     * List to store hobbies
     */
    private List<HobbyAttributes> hobbies;

    /**
     * Model class object for hobby
     */
    private HobbyAttributes statementhobby1;

    /**
     * Model class object for hobby
     */
    private HobbyAttributes hobby2;

    /**
     * Model class object for hobby
     */
    private HobbyAttributes hobby3;

    /**
     * Model class object for hobby
     */
    private HobbyAttributes hobby4;

    /**
     * Model class object for hobby
     */
    private HobbyAttributes hobby5;


    /**
     * Setup mocks hobbies before testing
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        badmintonDao = new BadmintonDao();
        statementhobby1 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "badminton", 4, "win");
        hobby2 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-04"), "badminton", 4, "win");
        hobby3 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-05"), "badminton", 4, "win");
        hobby4 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-07"), "badminton", 4, "win");
        hobby5 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-08"), "badminton", 4, "win");

        hobbies = new ArrayList<>();
        hobbies.add(statementhobby1);
        hobbies.add(hobby2);
        hobbies.add(hobby3);
        hobbies.add(hobby4);
        hobbies.add(hobby5);
    }

    @Test
    public void test() {
        assertEquals(1, 1);

    }


    /**
     * Test for whether data is retrieved user wise or not
     *
     * @throws SQLException
     */
    @Test
    @Ignore
    public void getBadmintonDataUserWise() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
//        when(HobbyAttributes.convertToList(resultSet)).thenReturn(hobbies);
//        assertEquals(hobbies, badmintonDao.getBadmintonDataUserWise(connection, 1));
    }

    /**
     * Test for inserting badminton tick data to database
     *
     * @throws SQLException
     * @throws ApplicationRuntimeException
     */
    @Test
    @Ignore
    public void hobbyBadmintonTick() throws SQLException, ApplicationRuntimeException {

        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);
        doNothing().when(statement).setInt(anyInt(), anyInt());
        badmintonDao.hobbyBadmintonTick(connection, (Badminton) statementhobby1);

        verify(connection.prepareStatement(anyString()), times(1)).executeUpdate();

    }

}
