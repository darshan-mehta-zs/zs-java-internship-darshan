package com.zs.hobbytracker.service;

import com.zs.hobbytracker.dao.ChessDao;
import com.zs.hobbytracker.exception.ApplicationRuntimeException;
import com.zs.hobbytracker.exception.InvalidInputException;
import com.zs.hobbytracker.models.Chess;
import com.zs.hobbytracker.models.HobbyAttributes;
import com.zs.hobbytracker.validator.Validator;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Service for Chess hobby
 */
public class ChessService implements HobbyService {

    ChessDao chessDao;

    /**
     * Constructor which injects chess dao as dependency
     */
    public ChessService() {
        chessDao = new ChessDao();
    }

    /**
     * calls dao to store chess hobby data to database
     *
     * @param connection accepts connection to database
     * @param chess      accepts chess object to be stored to database
     * @throws InvalidInputException       in case if there is exception raises from input provided
     * @throws ApplicationRuntimeException
     */
    public void tick(Connection connection, HobbyAttributes chess) throws InvalidInputException, ApplicationRuntimeException {
        Validator.validate(chess);
        chessDao.hobbyChessTick(connection, (Chess) chess);
    }

    /**
     * Finds longest streak for chess hobby in number of days
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return An Integer value for longest streak of chess hobby in number of days
     * @throws ApplicationRuntimeException
     */
    public int getLongestStreak(Connection connection, int userId) throws ApplicationRuntimeException {
        List<HobbyAttributes> hobbies = chessDao.getChessDataUserWise(connection, userId);
        return HobbyAttributes.getLongestStreak(hobbies);
    }

    /**
     * Finds latest streak for chess hobby in number of days
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return An Integer value for longest streak of chess in number of days
     * @throws ApplicationRuntimeException
     */
    public int getLatestStreak(Connection connection, int userId) throws ApplicationRuntimeException {
        List<HobbyAttributes> hobbies = chessDao.getChessDataUserWise(connection, userId);
        return HobbyAttributes.getLatestStreak(hobbies);
    }

    /**
     * Fetch details of when was the last time user performed the hobby
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return chess object containing data
     * @throws ApplicationRuntimeException
     */
    public Chess lastTick(Connection connection, int userId) throws ApplicationRuntimeException {
        return chessDao.lastTick(connection, userId);
    }

    /**
     * Fetch details of chess hobby performed by user on given date
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @param date       accepts date
     * @return Chess object containing data
     * @throws ApplicationRuntimeException
     */
    public Chess detailsForDate(Connection connection, int userId, Date date) throws ApplicationRuntimeException {
        return chessDao.detailsForDate(connection, userId, date);
    }
}
