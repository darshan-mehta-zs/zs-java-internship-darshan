package com.zs.hobbytracker.service;

import com.zs.hobbytracker.dao.BadmintonDao;
import com.zs.hobbytracker.exception.ApplicationRuntimeException;
import com.zs.hobbytracker.exception.InvalidInputException;
import com.zs.hobbytracker.models.Badminton;
import com.zs.hobbytracker.models.HobbyAttributes;
import com.zs.hobbytracker.validator.Validator;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Service for Badminton hobby
 */
@Service
public class BadmintonService implements HobbyService {

    BadmintonDao badmintonDao;

    /**
     * Constructor which injects dao as dependency
     */
    public BadmintonService() {
        badmintonDao = new BadmintonDao();
    }

    /**
     * @param badmintonDao sets the dao
     */
    public BadmintonService(BadmintonDao badmintonDao) {
        this.badmintonDao = badmintonDao;
    }

    /**
     * calls dao to store badminton hobby data to database
     *
     * @param connection accepts connection to database
     * @param badminton  accepts badminton object to be stored to database
     * @throws InvalidInputException       in case if there is exception raises from input provided
     * @throws ApplicationRuntimeException
     */
    public void tick(Connection connection, HobbyAttributes badminton) throws InvalidInputException, ApplicationRuntimeException {
        Validator.validate(badminton);
        badmintonDao.hobbyBadmintonTick(connection, (Badminton) badminton);
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
        List<HobbyAttributes> hobbies = badmintonDao.getBadmintonDataUserWise(connection, userId);
        return HobbyAttributes.getLongestStreak(hobbies);
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
        List<HobbyAttributes> hobbies = badmintonDao.getBadmintonDataUserWise(connection, userId);
        return HobbyAttributes.getLatestStreak(hobbies);
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
        return badmintonDao.lastTick(connection, userId);
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
        return badmintonDao.detailsForDate(connection, userId, date);
    }
}
